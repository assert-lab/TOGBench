cd "$PWD/projects_decomposed/commons-net"
echo $PWD

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [
    "MacOsPeterFTPEntryParserTest.testParseFieldsOnFile*",
    "OS400FTPEntryParserTest.testParseFieldsOnFile*",
    "OS400FTPEntryParserAdditionalTest.testParseFieldsOnFile*",
    "EnterpriseUnixFTPEntryParserTest.testParseFieldsOnFile*",
    "VMSFTPEntryParserTest.testParseFieldsOnFile*",
    "MVSFTPEntryParserTest.testParseFieldsOnFile*",
    "OS2FTPEntryParserTest.testParseFieldsOnFile*",
    "NTFTPEntryParserTest.testParseFieldsOnFile*",
    "UnixFTPEntryParserTest.testParseFieldsOnFile*",
    "NetwareFTPEntryParserTest.testParseFieldsOnFile*",
    "TerminalTypeOptionHandlerTest.testAnswerSubnegotiation*",
    "SuppressGAOptionHandlerTest.testAnswerSubnegotiation*",
    "WindowSizeOptionHandlerTest.testAnswerSubnegotiation*",
    "SimpleOptionHandlerTest.testAnswerSubnegotiation*",
    "EchoOptionHandlerTest.testAnswerSubnegotiation*",
    "MacOsPeterFTPEntryParserTest.testParseFieldsOnDirectory*", 
"OS400FTPEntryParserTest.testParseFieldsOnDirectory*", 
"VMSFTPEntryParserTest.testParseFieldsOnDirectory*", 
"OS400FTPEntryParserAdditionalTest.testParseFieldsOnDirectory*", 
"MVSFTPEntryParserTest.testParseFieldsOnDirectory*", 
"OS2FTPEntryParserTest.testParseFieldsOnDirectory*", 
"NTFTPEntryParserTest.testParseFieldsOnDirectory*", 
"UnixFTPEntryParserTest.testParseFieldsOnDirectory*", 
"NetwareFTPEntryParserTest.testParseFieldsOnDirectory*", 
"TerminalTypeOptionHandlerTest.testStartSubnegotiation*", 
"SuppressGAOptionHandlerTest.testStartSubnegotiation*", 
"WindowSizeOptionHandlerTest.testStartSubnegotiation*", 
"SimpleOptionHandlerTest.testStartSubnegotiation*", 
"EchoOptionHandlerTest.testStartSubnegotiation*", 
"SocketClientFunctionalTest.testProxySettings_1_oe",

"POP3ClientCommandsTest.testDeleteWithReset_1_oe",
"POP3ClientCommandsTest.testDeleteWithReset_2_oe",
"POP3ClientCommandsTest.testDelete_1_oe",
"POP3ClientCommandsTest.testDelete_2_oe",
"POP3ClientCommandsTest.testListMessageOnEmptyMailbox_1_oe",
"POP3ClientCommandsTest.testListMessageOnEmptyMailbox_2_oe",
"POP3ClientCommandsTest.testListMessageOnFullMailbox_1_oe",
"POP3ClientCommandsTest.testListMessageOnFullMailbox_2_oe",
"POP3ClientCommandsTest.testListMessageOnFullMailbox_3_oe",
"POP3ClientCommandsTest.testListMessageOnFullMailbox_4_oe",
"POP3ClientCommandsTest.testListMessageOnFullMailbox_5_oe",
"POP3ClientCommandsTest.testListMessageOnFullMailbox_6_oe",
"POP3ClientCommandsTest.testListMessageOnFullMailbox_7_oe",
"POP3ClientCommandsTest.testListMessageOnFullMailbox_8_oe",
"POP3ClientCommandsTest.testListMessagesOnEmptyMailbox_1_oe",
"POP3ClientCommandsTest.testListMessagesOnEmptyMailbox_2_oe",
"POP3ClientCommandsTest.testListMessagesOnEmptyMailbox_3_oe",
"POP3ClientCommandsTest.testListMessagesOnFullMailbox_1_oe",
"POP3ClientCommandsTest.testListMessagesOnFullMailbox_2_oe",
"POP3ClientCommandsTest.testListMessagesOnFullMailbox_3_oe",
"POP3ClientCommandsTest.testListMessagesOnFullMailbox_4_oe",
"POP3ClientCommandsTest.testListMessagesOnFullMailbox_5_oe",
"POP3ClientCommandsTest.testListMessagesOnFullMailbox_6_oe",
"POP3ClientCommandsTest.testListUniqueIDOnFullMailbox_1_oe",
"POP3ClientCommandsTest.testListUniqueIDOnFullMailbox_2_oe",
"POP3ClientCommandsTest.testListUniqueIDOnFullMailbox_3_oe",
"POP3ClientCommandsTest.testListUniqueIDOnFullMailbox_4_oe",
"POP3ClientCommandsTest.testListUniqueIDOnFullMailbox_5_oe",
"POP3ClientCommandsTest.testListUniqueIDOnFullMailbox_6_oe",
"POP3ClientCommandsTest.testListUniqueIDOnFullMailbox_7_oe",
"POP3ClientCommandsTest.testListUniqueIDsOnEmptyMailbox_1_oe",
"POP3ClientCommandsTest.testListUniqueIDsOnEmptyMailbox_2_oe",
"POP3ClientCommandsTest.testListUniqueIDsOnEmptyMailbox_3_oe",
"POP3ClientCommandsTest.testListUniqueIDsOnFullMailbox_1_oe",
"POP3ClientCommandsTest.testListUniqueIDsOnFullMailbox_2_oe",
"POP3ClientCommandsTest.testListUniqueIDsOnFullMailbox_3_oe",
"POP3ClientCommandsTest.testListUniqueIDsOnFullMailbox_4_oe",
"POP3ClientCommandsTest.testListUniqueIDsOnFullMailbox_5_oe",
"POP3ClientCommandsTest.testListUniqueIdentifierOnEmptyMailbox_1_oe",
"POP3ClientCommandsTest.testListUniqueIdentifierOnEmptyMailbox_2_oe",
"POP3ClientCommandsTest.testNoopCommand_1_oe",
"POP3ClientCommandsTest.testNoopCommand_2_oe",
"POP3ClientCommandsTest.testNoopCommand_3_oe",
"POP3ClientCommandsTest.testResetAndDeleteShouldFails_1_oe",
"POP3ClientCommandsTest.testResetAndDeleteShouldFails_2_oe",
"POP3ClientCommandsTest.testRetrieveMessageOnEmptyMailbox_1_oe",
"POP3ClientCommandsTest.testRetrieveMessageOnEmptyMailbox_2_oe",
"POP3ClientCommandsTest.testRetrieveMessageOnFullMailbox_1_oe",
"POP3ClientCommandsTest.testRetrieveMessageOnFullMailbox_2_oe",
"POP3ClientCommandsTest.testRetrieveMessageOnFullMailbox_3_oe",
"POP3ClientCommandsTest.testRetrieveMessageShouldFails_1_oe",
"POP3ClientCommandsTest.testRetrieveMessageShouldFails_2_oe",
"POP3ClientCommandsTest.testRetrieveMessageShouldFails_3_oe",
"POP3ClientCommandsTest.testRetrieveMessageShouldFails_4_oe",
"POP3ClientCommandsTest.testRetrieveMessageTopOnEmptyMailbox_1_oe",
"POP3ClientCommandsTest.testRetrieveMessageTopOnEmptyMailbox_2_oe",
"POP3ClientCommandsTest.testRetrieveMessageTopOnFullMailbox_1_oe",
"POP3ClientCommandsTest.testRetrieveMessageTopOnFullMailbox_2_oe",
"POP3ClientCommandsTest.testRetrieveMessageTopShouldFails_1_oe",
"POP3ClientCommandsTest.testRetrieveMessageTopShouldFails_2_oe",
"POP3ClientCommandsTest.testRetrieveMessageTopShouldFails_3_oe",
"POP3ClientCommandsTest.testRetrieveMessageTopShouldFails_4_oe",
"POP3ClientCommandsTest.testRetrieveOverSizedMessageTopOnFullMailbox_1_oe",
"POP3ClientCommandsTest.testRetrieveOverSizedMessageTopOnFullMailbox_2_oe",
"POP3ClientCommandsTest.testStatus_1_oe",
"POP3ClientCommandsTest.testStatus_2_oe",
"POP3ClientCommandsTest.testStatus_3_oe",
"POP3ClientCommandsTest.testStatus_4_oe",
"POP3ClientCommandsTest.testStatus_5_oe",
"POP3ClientCommandsTest.testStatus_6_oe",
"POP3ClientCommandsTest.testStatus_7_oe",
"POP3ClientCommandsTest.testStatus_8_oe",
"POP3ClientCommandsTest.testStatus_9_oe",

"POP3ClientTest.testInvalidLoginWithBadName_1_oe",
"POP3ClientTest.testInvalidLoginWithBadPassword_1_oe",
"POP3ClientTest.testLoginFromWrongState_2_oe",
"POP3ClientTest.testLoginFromWrongState_3_oe",
"POP3ClientTest.testLogoutFromAllStates_1_oe",
"POP3ClientTest.testLogoutFromAllStates_2_oe",
"POP3ClientTest.testLogoutFromAllStates_3_oe",

"POP3ConstructorTest.testPOP3ClientStateTransition_5_oe",
"POP3ConstructorTest.testPOP3ClientStateTransition_6_oe",
"POP3ConstructorTest.testPOP3ClientStateTransition_7_oe",
"POP3ConstructorTest.testPOP3ClientStateTransition_8_oe",
"POP3ConstructorTest.testPOP3ClientStateTransition_9_oe",
"POP3ConstructorTest.testPOP3ClientStateTransition_10_oe",
"POP3ConstructorTest.testPOP3ClientStateTransition_11_oe",
"POP3ConstructorTest.testPOP3ClientStateTransition_12_oe",
"POP3ConstructorTest.testPOP3ClientStateTransition_13_oe",
"POP3ConstructorTest.testPOP3ClientStateTransition_14_oe",
"POP3ConstructorTest.testPOP3ClientStateTransition_15_oe",
"POP3ConstructorTest.testPOP3ClientStateTransition_16_oe",
"POP3ConstructorTest.testPOP3ClientStateTransition_17_oe",


# "ListingFunctionalTest.*",
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
