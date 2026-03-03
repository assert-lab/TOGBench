# python3 scripts/comment-incompatible_assertions.py

import pandas as pd
import os, re, csv, argparse, sys
from pathlib import Path
from collections import defaultdict


file_to_error_lines =  defaultdict(set)
def comment_toga_assertion(path):

    with open(path, 'r') as f:
        lines = f.readlines()
        for line in lines:
            if "[ERROR] /home/tasfia/Desktop" in line:
                start = line.find('/home/tasfia/Desktop')
                end = line.find('.java', start)+5
                file_path= line[start:end]
                start = line.find('java:[')+6
                end = line.find(',', start)
                lineNo=line[start:end]
                file_to_error_lines[file_path].add(lineNo)
    f.close()

    for key in file_to_error_lines:
        path = key
        lineNo=file_to_error_lines[key]
        print(lineNo)

        lineCount=0;
        with open(path, 'r+') as f:
            lines = f.readlines()
            f.seek(0)
            f.truncate()
            for line in lines:
                lineCount=lineCount+1
                if str(lineCount) in lineNo and ("assert" in line or "Assert" in line):
                    line="// incorrect assertion "+line
                f.write(line)
        f.close()

if __name__ == "__main__":

    path="projects_decomposed/JSON-java/mvn_log.txt"
    comment_toga_assertion(path)
