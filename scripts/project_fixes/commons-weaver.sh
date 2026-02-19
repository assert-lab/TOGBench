
cd "$PWD/projects_decomposed/commons-weaver"
echo $PWD

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset_final")
meta_path = dataset / "meta_final.csv"
inputs_path = dataset / "inputs_final.csv"

fail_list = [

"WeaveProcessorTest.testWeaveVisiting_5_oe",
"WeaveProcessorTest.testWeaveVisiting_7_oe",
"FinderTest.testElements_100_oe",
"FinderTest.testElements_101_oe",
"FinderTest.testElements_102_oe",
"FinderTest.testElements_103_oe",
"FinderTest.testElements_104_oe",
"FinderTest.testElements_105_oe",
"FinderTest.testElements_106_oe",
"FinderTest.testElements_107_oe",
"FinderTest.testElements_108_oe",
"FinderTest.testElements_109_oe",
"FinderTest.testElements_10_oe",
"FinderTest.testElements_110_oe",
"FinderTest.testElements_111_oe",
"FinderTest.testElements_112_oe",
"FinderTest.testElements_113_oe",
"FinderTest.testElements_114_oe",
"FinderTest.testElements_115_oe",
"FinderTest.testElements_116_oe",
"FinderTest.testElements_117_oe",
"FinderTest.testElements_118_oe",
"FinderTest.testElements_119_oe",
"FinderTest.testElements_11_oe",
"FinderTest.testElements_120_oe",
"FinderTest.testElements_121_oe",
"FinderTest.testElements_122_oe",
"FinderTest.testElements_123_oe",
"FinderTest.testElements_124_oe",
"FinderTest.testElements_125_oe",
"FinderTest.testElements_126_oe",
"FinderTest.testElements_127_oe",
"FinderTest.testElements_128_oe",
"FinderTest.testElements_129_oe",
"FinderTest.testElements_12_oe",
"FinderTest.testElements_130_oe",
"FinderTest.testElements_131_oe",
"FinderTest.testElements_132_oe",
"FinderTest.testElements_133_oe",
"FinderTest.testElements_134_oe",
"FinderTest.testElements_135_oe",
"FinderTest.testElements_136_oe",
"FinderTest.testElements_137_oe",
"FinderTest.testElements_138_oe",
"FinderTest.testElements_139_oe",
"FinderTest.testElements_13_oe",
"FinderTest.testElements_140_oe",
"FinderTest.testElements_141_oe",
"FinderTest.testElements_142_oe",
"FinderTest.testElements_143_oe",
"FinderTest.testElements_144_oe",
"FinderTest.testElements_145_oe",
"FinderTest.testElements_146_oe",
"FinderTest.testElements_147_oe",
"FinderTest.testElements_148_oe",
"FinderTest.testElements_149_oe",
"FinderTest.testElements_14_oe",
"FinderTest.testElements_150_oe",
"FinderTest.testElements_151_oe",
"FinderTest.testElements_152_oe",
"FinderTest.testElements_153_oe",
"FinderTest.testElements_154_oe",
"FinderTest.testElements_155_oe",
"FinderTest.testElements_156_oe",
"FinderTest.testElements_157_oe",
"FinderTest.testElements_15_oe",
"FinderTest.testElements_16_oe",
"FinderTest.testElements_17_oe",
"FinderTest.testElements_18_oe",
"FinderTest.testElements_19_oe",
"FinderTest.testElements_1_oe",
"FinderTest.testElements_20_oe",
"FinderTest.testElements_21_oe",
"FinderTest.testElements_22_oe",
"FinderTest.testElements_23_oe",
"FinderTest.testElements_24_oe",
"FinderTest.testElements_25_oe",
"FinderTest.testElements_26_oe",
"FinderTest.testElements_27_oe",
"FinderTest.testElements_28_oe",
"FinderTest.testElements_29_oe",
"FinderTest.testElements_2_oe",
"FinderTest.testElements_30_oe",
"FinderTest.testElements_31_oe",
"FinderTest.testElements_32_oe",
"FinderTest.testElements_33_oe",
"FinderTest.testElements_34_oe",
"FinderTest.testElements_35_oe",
"FinderTest.testElements_36_oe",
"FinderTest.testElements_37_oe",
"FinderTest.testElements_38_oe",
"FinderTest.testElements_39_oe",
"FinderTest.testElements_3_oe",
"FinderTest.testElements_40_oe",
"FinderTest.testElements_41_oe",
"FinderTest.testElements_42_oe",
"FinderTest.testElements_43_oe",
"FinderTest.testElements_44_oe",
"FinderTest.testElements_45_oe",
"FinderTest.testElements_46_oe",
"FinderTest.testElements_47_oe",
"FinderTest.testElements_48_oe",
"FinderTest.testElements_49_oe",
"FinderTest.testElements_4_oe",
"FinderTest.testElements_50_oe",
"FinderTest.testElements_51_oe",
"FinderTest.testElements_52_oe",
"FinderTest.testElements_53_oe",
"FinderTest.testElements_54_oe",
"FinderTest.testElements_55_oe",
"FinderTest.testElements_56_oe",
"FinderTest.testElements_57_oe",
"FinderTest.testElements_58_oe",
"FinderTest.testElements_59_oe",
"FinderTest.testElements_5_oe",
"FinderTest.testElements_60_oe",
"FinderTest.testElements_61_oe",
"FinderTest.testElements_62_oe",
"FinderTest.testElements_63_oe",
"FinderTest.testElements_64_oe",
"FinderTest.testElements_65_oe",
"FinderTest.testElements_66_oe",
"FinderTest.testElements_67_oe",
"FinderTest.testElements_68_oe",
"FinderTest.testElements_69_oe",
"FinderTest.testElements_6_oe",
"FinderTest.testElements_70_oe",
"FinderTest.testElements_71_oe",
"FinderTest.testElements_72_oe",
"FinderTest.testElements_73_oe",
"FinderTest.testElements_74_oe",
"FinderTest.testElements_75_oe",
"FinderTest.testElements_76_oe",
"FinderTest.testElements_77_oe",
"FinderTest.testElements_78_oe",
"FinderTest.testElements_79_oe",
"FinderTest.testElements_7_oe",
"FinderTest.testElements_80_oe",
"FinderTest.testElements_81_oe",
"FinderTest.testElements_82_oe",
"FinderTest.testElements_83_oe",
"FinderTest.testElements_84_oe",
"FinderTest.testElements_85_oe",
"FinderTest.testElements_86_oe",
"FinderTest.testElements_87_oe",
"FinderTest.testElements_88_oe",
"FinderTest.testElements_89_oe",
"FinderTest.testElements_8_oe",
"FinderTest.testElements_90_oe",
"FinderTest.testElements_91_oe",
"FinderTest.testElements_92_oe",
"FinderTest.testElements_93_oe",
"FinderTest.testElements_94_oe",
"FinderTest.testElements_95_oe",
"FinderTest.testElements_96_oe",
"FinderTest.testElements_97_oe",
"FinderTest.testElements_98_oe",
"FinderTest.testElements_99_oe",
"FinderTest.testElements_9_oe",
"FinderTest.testObjectMethods_1_oe",
"FinderTest.testObjectMethods_2_oe",
"FinderTest.testObjectMethods_3_oe",



]

patterns = []
for full in fail_list:
    cls, name = full.split(".", 1)
    if name.endswith("*"):
        name = name[:-1]  # strip '*'
    patterns.append((cls, name))

bad_ids = set()
meta_rows = []
failed_meta_rows = []
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
            failed_meta_rows.append(row)
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
failed_inputs_rows = []
removed_inputs = 0

with inputs_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    in_fields = r.fieldnames
    for row in r:
        if row["id"] in bad_ids:
            failed_inputs_rows.append(row)
            removed_inputs += 1
            continue
        inputs_rows.append(row)

print("removed from inputs:", removed_inputs)

with inputs_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=in_fields)
    w.writeheader()
    w.writerows(inputs_rows)

def merge_dedup_by_id(path, base_fieldnames, new_rows):
    if not new_rows and not path.exists():
        return

    merged = {}
    all_fields = list(base_fieldnames or [])

    def add_fields_from_row(row):
        nonlocal all_fields
        for k in row.keys():
            if k not in all_fields:
                all_fields.append(k)

    if path.exists():
        with path.open(newline="", encoding="utf-8") as f:
            r = csv.DictReader(f)
            if r.fieldnames:
                for k in r.fieldnames:
                    if k not in all_fields:
                        all_fields.append(k)
            for row in r:
                rid = (row.get("id") or "").strip()
                if rid:
                    add_fields_from_row(row)
                    merged[rid] = row

    for row in new_rows:
        rid = (row.get("id") or "").strip()
        if rid:
            add_fields_from_row(row)
            merged[rid] = row

    with path.open("w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=all_fields, lineterminator="\n", extrasaction="ignore")
        w.writeheader()
        for rid in sorted(merged.keys()):
            rr = dict(merged[rid])
            for k in all_fields:
                rr.setdefault(k, "")
            w.writerow(rr)


meta_failed_path = dataset / "meta_mvn_failed.csv"
merge_dedup_by_id(meta_failed_path, fieldnames, failed_meta_rows)

inputs_failed_path = dataset / "inputs_mvn_failed.csv"
merge_dedup_by_id(inputs_failed_path, in_fields, failed_inputs_rows)


PY
