#!/usr/bin/env python3
"""
Diagnostic script to debug test extraction issues.
Shows exactly what's being parsed and where it might be going wrong.
"""
import os
import sys
import re
from typing import List

# You'll need to adjust these based on your actual utils.py
def sanitize_for_counting(line: str, in_block_comment: bool):
    """Remove comments for counting braces"""
    result = line
    new_in_block = in_block_comment
    
    if in_block_comment:
        if '*/' in line:
            idx = line.find('*/')
            result = line[idx + 2:]
            new_in_block = False
        else:
            result = ''
    else:
        if '/*' in line:
            start = line.find('/*')
            if '*/' in line[start:]:
                end = line.find('*/', start)
                result = line[:start] + line[end + 2:]
            else:
                result = line[:start]
                new_in_block = True
        
        if '//' in result and not new_in_block:
            result = result.split('//')[0]
    
    return result, new_in_block


JUNIT3_TEST_HEADER = re.compile(r"\s*public\s+void\s+test[A-Za-z0-9_]*\s*\(")

JUNIT_TEST_ANNOTATIONS = (
    "@Test",
    "@org.junit.Test",
    "@org.junit.jupiter.api.Test",
    "@ParameterizedTest",
    "@RepeatedTest",
    "@TestFactory",
    "@TestTemplate",
)


def _line_has_test_annotation(line: str, in_block_comment: bool) -> bool:
    sanitized, _ = sanitize_for_counting(line, in_block_comment)
    s = sanitized.strip()
    if not s.startswith("@"):
        return False
    return any(ann in s for ann in JUNIT_TEST_ANNOTATIONS)


def extract_test_methods_debug(file_content: str, filename: str) -> List[List[str]]:
    """Extract test methods with debug output"""
    lines = file_content.splitlines(keepends=True)
    methods: List[List[str]] = []
    i = 0
    n = len(lines)
    in_block = False
    
    print(f"\n{'='*80}")
    print(f"Parsing: {filename}")
    print(f"Total lines: {n}")
    print(f"{'='*80}\n")

    while i < n:
        line = lines[i]
        sanitized, in_block = sanitize_for_counting(line, in_block)

        # Check for JUnit 4+ annotations
        if _line_has_test_annotation(line, in_block_comment=False):
            print(f"[Line {i+1}] Found @Test annotation: {line.strip()}")
            buf: List[str] = [line]
            i += 1
            brace_balance = 0
            saw_open = False
            inner_in_block = in_block
            start_line = i

            while i < n:
                ln = lines[i]
                buf.append(ln)
                s2, inner_in_block = sanitize_for_counting(ln, inner_in_block)
                opens = s2.count("{")
                closes = s2.count("}")
                brace_balance += opens - closes
                
                if opens > 0:
                    if not saw_open:
                        print(f"  [Line {i+1}] Method body starts, balance: {brace_balance}")
                    saw_open = True
                
                if saw_open and brace_balance == 0:
                    print(f"  [Line {i+1}] Method complete (balanced braces), total lines: {len(buf)}")
                    i += 1
                    break
                i += 1
            
            if not saw_open:
                print(f"  WARNING: Never found opening brace for method starting at line {start_line}")
            elif brace_balance != 0:
                print(f"  WARNING: Unbalanced braces (balance={brace_balance}) for method starting at line {start_line}")
            
            methods.append(buf)
            continue

        # Check for JUnit 3 style tests
        if JUNIT3_TEST_HEADER.match(sanitized):
            print(f"[Line {i+1}] Found JUnit3 test: {sanitized.strip()}")
            buf = [line]
            i += 1
            brace_balance = sanitized.count("{") - sanitized.count("}")
            saw_open = sanitized.count("{") > 0
            inner_in_block = in_block
            start_line = i

            while i < n:
                ln = lines[i]
                buf.append(ln)
                s2, inner_in_block = sanitize_for_counting(ln, inner_in_block)
                opens = s2.count("{")
                closes = s2.count("}")
                brace_balance += opens - closes
                if opens > 0:
                    saw_open = True
                if saw_open and brace_balance == 0:
                    print(f"  [Line {i+1}] Method complete, total lines: {len(buf)}")
                    i += 1
                    break
                i += 1
            
            methods.append(buf)
            continue

        i += 1

    print(f"\nTotal methods extracted: {len(methods)}\n")
    return methods


def analyze_test_file(filepath: str):
    """Analyze a single test file in detail"""
    print(f"\n{'#'*80}")
    print(f"# ANALYZING FILE: {os.path.basename(filepath)}")
    print(f"{'#'*80}")
    
    try:
        with open(filepath, 'r', encoding='utf-8', errors='ignore') as f:
            content = f.read()
    except Exception as e:
        print(f"ERROR reading file: {e}")
        return
    
    # Basic stats
    print(f"\nFile size: {len(content)} chars")
    print(f"Lines: {len(content.splitlines())}")
    
    # Check what test framework is used
    has_junit3 = bool(JUNIT3_TEST_HEADER.search(content))
    has_junit4_test = '@Test' in content or '@org.junit.Test' in content
    
    print(f"\nFramework detection:")
    print(f"  JUnit 3 style (public void testXxx): {has_junit3}")
    print(f"  JUnit 4+ (@Test): {has_junit4_test}")
    
    # Count potential test methods manually
    junit3_count = len(JUNIT3_TEST_HEADER.findall(content))
    junit4_count = content.count('@Test')
    
    print(f"\nRaw counts:")
    print(f"  JUnit 3 pattern matches: {junit3_count}")
    print(f"  '@Test' occurrences: {junit4_count}")
    
    # Extract and show what we parsed
    methods = extract_test_methods_debug(content, os.path.basename(filepath))
    
    if methods:
        print(f"\nShowing first method details:")
        first_method = methods[0]
        print(f"Lines in method: {len(first_method)}")
        print(f"\nFirst 15 lines:")
        for idx, line in enumerate(first_method[:15], 1):
            print(f"  {idx:3d}: {line.rstrip()}")
        if len(first_method) > 15:
            print(f"  ... ({len(first_method) - 15} more lines)")
        
        # Check for assertions
        method_text = ''.join(first_method)
        assert_count = method_text.count('assert')
        assertEquals_count = method_text.count('assertEquals')
        print(f"\nAssertion analysis in first method:")
        print(f"  'assert' occurrences: {assert_count}")
        print(f"  'assertEquals' occurrences: {assertEquals_count}")
    else:
        print("\n⚠️  NO METHODS EXTRACTED - Showing sample lines with 'test':")
        lines = content.splitlines()
        for i, line in enumerate(lines, 1):
            lower = line.lower()
            if 'test' in lower and ('public' in lower or '@' in line):
                print(f"  Line {i}: {line.strip()}")


def main():
    if len(sys.argv) < 2:
        print("Usage: python diagnose_parsing.py <path_to_test_file_or_directory>")
        print("\nExamples:")
        print("  python diagnose_parsing.py projects_decomposed/commons-collections-4.4/")
        print("  python diagnose_parsing.py projects_decomposed/commons-collections-4.4/src/test/java/org/apache/commons/collections4/SomeTest.java")
        sys.exit(1)
    
    path = sys.argv[1]
    
    if os.path.isfile(path):
        analyze_test_file(path)
    elif os.path.isdir(path):
        # Find test files
        test_files = []
        for root, _, files in os.walk(path):
            if os.sep + 'src' + os.sep + 'test' + os.sep + 'java' + os.sep in root:
                for f in files:
                    if f.endswith('.java'):
                        test_files.append(os.path.join(root, f))
        
        if not test_files:
            print(f"No test files found in {path}")
            print("Looking for ANY .java files with 'Test' in name:")
            for root, _, files in os.walk(path):
                for f in files:
                    if f.endswith('.java') and 'Test' in f:
                        print(f"  Found: {os.path.join(root, f)}")
        else:
            print(f"Found {len(test_files)} test files")
            # Analyze first 3
            for test_file in test_files[:3]:
                analyze_test_file(test_file)
    else:
        print(f"Path not found: {path}")


if __name__ == "__main__":
    main()