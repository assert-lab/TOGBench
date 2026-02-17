cd "$PWD/projects_decomposed/commons-jcs3"
echo $PWD

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"CacheTest.getPut_1_oe",
"CacheTest.getPut_2_oe",
"CacheTest.getPut_3_oe",
"CacheTest.getPut_4_oe",
"CacheTest.listeners_1_oe",
"CacheTest.listeners_2_oe",
"CacheTest.listeners_3_oe",
"CacheTest.listeners_4_oe",
"CacheTest.listeners_5_oe",
"CacheTest.listeners_6_oe",
"CacheTest.loader_1_oe",
"ExpiryListenerTest.listener_1_oe",
"ExpiryListenerTest.listener_2_oe",
"ExpiryListenerTest.listener_3_oe",
"ImmediateExpiryTest.immediate_2_oe",
"NotSerializableTest.run_1_oe",
"NotSerializableTest.run_2_oe",
"NotSerializableTest.run_3_oe",
"NotSerializableTest.run_4_oe",
"CacheLoaderAdapterTest.checkLoadAll_3_oe",
"CacheLoaderAdapterTest.checkLoadAll_6_oe",
"OpenJPAJCacheDataCacheTest.query_1_oe",
"OpenJPAJCacheDataCacheTest.query_2_oe",
"OpenJPAJCacheDataCacheTest.query_3_oe",
"OpenJPAJCacheDataCacheTest.query_4_oe",
"OpenJPAJCacheDataCacheTest.query_5_oe",
"OpenJPAJCacheDataCacheTest.query_6_oe",
"RemoteHttpCacheServiceUnitTest.testGet_simple_2_oe",
"RemoteHttpCacheServiceUnitTest.testRemoveAll_simple_1_oe",
"RemoteHttpCacheServiceUnitTest.testRemoveAll_simple_2_oe",
"RemoteHttpCacheServiceUnitTest.testRemove_simple_1_oe",
"RemoteHttpCacheServiceUnitTest.testRemove_simple_2_oe",
"RemoteHttpCacheServiceUnitTest.testUpdate_simple_1_oe",
"RemoteHttpCacheServiceUnitTest.testUpdate_simple_2_oe",
"RemoteHttpCacheServletUnitTest.setUp",
"BasicRemoteCacheClientServerUnitTest.setup",
"RemoteCacheServerFactoryUnitTest.testConfigureObjectSpecificCustomFactory_withProperty_1_oe",
"RemoteCacheServerFactoryUnitTest.testConfigureObjectSpecificCustomFactory_withProperty_2_oe",
"RemoteCacheServerFactoryUnitTest.testConfigureObjectSpecificCustomFactory_withProperty_TimeoutConfigurableRMIScoketFactory_1_oe",
"RemoteCacheServerFactoryUnitTest.testConfigureObjectSpecificCustomFactory_withProperty_TimeoutConfigurableRMIScoketFactory_2_oe",
"RemoteCacheServerFactoryUnitTest.testConfigureObjectSpecificCustomFactory_withProperty_TimeoutConfigurableRMIScoketFactory_3_oe",
"RemoteCacheServerFactoryUnitTest.testConfigureRemoteCacheServerAttributes_allowClusterGetPresent_1_oe",
"RemoteCacheServerFactoryUnitTest.testConfigureRemoteCacheServerAttributes_eventQueuePoolName_1_oe",
"RemoteCacheServerFactoryUnitTest.testConfigureRemoteCacheServerAttributes_localClusterConsistencyPresent_1_oe",
"RemoteCacheServerFactoryUnitTest.testConfigureRemoteCacheServerAttributes_registryKeepAliveDelayMillisPresent_1_oe",
"RemoteCacheServerFactoryUnitTest.testConfigureRemoteCacheServerAttributes_rmiSocketFactoryTimeoutMillisPresent_1_oe",
"RemoteCacheServerFactoryUnitTest.testConfigureRemoteCacheServerAttributes_startRegistryPresent_1_oe",
"RemoteCacheServerFactoryUnitTest.testConfigureRemoteCacheServerAttributes_useRegistryKeepAlivePresent_1_oe",
"RemoteCacheServerUnitTest.setUp",
"RemoteCacheRequestFactoryUnitTest.testCreateGetMatchingRequest_Normal_1_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateGetMatchingRequest_Normal_2_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateGetMatchingRequest_Normal_3_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateGetMultipleRequest_Normal_1_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateGetMultipleRequest_Normal_2_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateGetMultipleRequest_Normal_3_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateGetRequest_Normal_1_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateGetRequest_Normal_2_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateGetRequest_Normal_3_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateRemoveAllRequest_Normal_1_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateRemoveAllRequest_Normal_2_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateRemoveAllRequest_Normal_3_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateRemoveRequest_Normal_1_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateRemoveRequest_Normal_2_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateRemoveRequest_Normal_3_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateUpdateRequest_Normal_1_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateUpdateRequest_Normal_2_oe",
"RemoteCacheRequestFactoryUnitTest.testCreateUpdateRequest_Normal_3_oe",
"SimpleEventHandlingUnitTest.testExceededIdletimeOnrequestEvent_2_oe",
"SimpleEventHandlingUnitTest.testExceededIdletimeOnrequestEvent_3_oe",
"SimpleEventHandlingUnitTest.testExceededMaxlifeOnrequestEvent_1_oe",
"SimpleEventHandlingUnitTest.testExceededMaxlifeOnrequestEvent_2_oe",

"LRUMapConcurrentUnitTest.*",
"JCSConcurrentCacheAccessUnitTest.*",
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
