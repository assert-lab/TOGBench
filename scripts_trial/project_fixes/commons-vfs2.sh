cd "$PWD/projects_decomposed/commons-vfs"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"SizeFileFilterTest.testSizeFileFilterZipDir_3_oe_1_oe",
"SizeFileFilterTest.testSizeFileFilterZipDir_11_oe_1_oe",
"SizeFileFilterTest.testSizeFileFilterZipDir_13_oe_1_oe",
"ContentTests.testContent_1_oe_1_oe",
"ContentTests.testContent_1_oe_2_oe",
"ContentTests.testContent_1_oe_3_oe",
"ContentTests.testContent_2_oe_1_oe",
"ContentTests.testContent_2_oe_3_oe",
"ContentTests.testReuse_3_oe_2_oe",
"ContentTests.testContent_2_oe_2_oe",
"ContentTests.testReuse_3_oe_1_oe",
"ContentTests.testReuse_3_oe_3_oe",
"ContentTests.testReuse_4_oe_2_oe",
"ContentTests.testReuse_4_oe_3_oe",
"ContentTests.testReuse_5_oe_1_oe",
"ContentTests.testReuse_5_oe_2_oe"
"ContentTests.testReuse_5_oe_3_oe",
"ContentTests.testReuse_4_oe_1_oe",
"ProviderReadTests.testStructure_1_oe_3_oe",
"ProviderReadTests.testStructure_1_oe_4_oe",
"EmptyFileFilterTest.testZipFile_1_oe_1_oe",
"AgeFileFilterTest.testAgeFileFilterLongBoolean_9_oe_1_oe",
"DirectoryAndFileFilterTest.testAcceptZipFile_1_oe_1_oe",
"DirectoryAndFileFilterTest.testAcceptZipFile_3_oe_1_oe",
"HiddenFileFilterTest.testZipFile_1_oe_1_oe",
"HiddenFileFilterTest.testZipFile_1_oe_2_oe",
"VfsClassLoaderTests.testLoadResource_2_oe_1_oe",
"VfsClassLoaderTests.testLoadResource_2_oe_2_oe",
"FileIteratorTest.testIterator_3_oe",
"FileIteratorTest.testIterator_4_oe",
"HdfsFileProviderTest.testRandomAccessContent_1_oe",
"HdfsFileProviderTest.testRandomAccessContent_2_oe",
"HdfsFileProviderTest.testRandomAccessContent_3_oe",
"CustomRamProviderTest.testReadNonEmptyFileByteByByte_2_oe",
"CustomRamProviderTest.testReadNonEmptyFileByteByByte_3_oe",
"CustomRamProviderTest.testReadNonEmptyFileByteByByte_4_oe",
"CustomRamProviderTest.testReadNonEmptyFileIntoBufferWithOffsetAndLength_2_oe",
"CustomRamProviderTest.testReadNonEmptyFileIntoBufferWithOffsetAndLength_3_oe",
"CustomRamProviderTest.testReadNonEmptyFileIntoBuffer_2_oe",
"CustomRamProviderTest.testReadNonEmptyFileIntoBuffer_3_oe",
"FileObjectSortTestCase.testSortArrayMoveAll_1_oe",

"Webdav4VersioningTests.*",
"PermissionsTests.*",
"ProviderDeleteTests.*",
"UrlTests.*",
"FileNameTests.*",
"WindowsFileNameTests.*",
"JunctionTests.*",
"UrlStructureTests.*",
"NamingTests.*",
"ProviderWriteAppendTests.*",
"LastModifiedTests.*",
"UrlTests.*",
"UriTests.*",
"VfsClassLoaderTests.*",
"ProviderRenameTests.*",
"ContentTests.*",
"ProviderWriteTests.*",
"NullFilesCacheTests.*",
"DefaultFilesCacheTests.*",
"LRUFilesCacheTests.*",
"WeakRefFilesCacheTests.*",
"SoftRefFilesCacheTests.*",
"ProviderReadTests.*",


]

patterns = []
for full in fail_list:
    cls, name = full.split(".", 1)
    if name.endswith("*"):
        name = name[:-1]  # strip '*'
    patterns.append((cls, name))

bad_ids = set()
meta_rows = []
removed_meta = 0

with meta_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    fieldnames = r.fieldnames
    for row in r:
        test_class = row["test_class"]
        test_name = row["test_name"]
        matched = False
        for cls_pattern, name_prefix in patterns:
            if test_class == cls_pattern and test_name.startswith(name_prefix):
                matched = True
                break
        if matched:
            bad_ids.add(row["id"])
            removed_meta += 1
        else:
            meta_rows.append(row)

print("removed from meta:", removed_meta)
print("bad_ids:", len(bad_ids))

with meta_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=fieldnames)
    w.writeheader()
    w.writerows(meta_rows)

inputs_rows = []
removed_inputs = 0

with inputs_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    in_fields = r.fieldnames
    for row in r:
        if row["id"] in bad_ids:
            removed_inputs += 1
            continue
        inputs_rows.append(row)

print("removed from inputs:", removed_inputs)

with inputs_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=in_fields)
    w.writeheader()
    w.writerows(inputs_rows)
PY
