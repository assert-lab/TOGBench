cd "$PWD/projects_decomposed/commons-jcs3"
echo $PWD

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset_final")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [
"SystemPropertyUnitTest.test1SystemPropertyInValueDelimiter_1_oe",
"JDBCDiskCacheShrinkUnitTest.testExpireInBackground_1_oe",
"HSQLDiskCacheUnitTest.testRemoveAllProhibition_1_oe",
"CompositeCacheConfiguratorUnitTest.testParseSpoolChunkSize_Normal_1_oe",
"CompositeCacheDiskUsageUnitTest.testUpdateConfig_1_oe",
"CompositeCacheManagerTest.testRelease_2_oe",
"SimpleEventHandlingUnitTest.testExceededIdletimeOnrequestEvent_1_oe",
"SimpleEventHandlingUnitTest.testSpoolEvent_1_oe",
"SimpleEventHandlingUnitTest.testSpoolNoDiskEvent_1_oe",
"SimpleEventHandlingUnitTest.testSpoolNotAllowedEventOnItem_1_oe",
"SimpleEventHandlingUnitTest.testSpoolNotAllowedEvent_1_oe",
"SoftReferenceMemoryCacheUnitTest.testPutGetThroughHub_1_oe",
"SerializerUnitTest.testReadWrite_1_oe",
"LRUMapPerformanceTest.testSimpleLoad_1_oe",
"LRUMapPerformanceTest.testSimpleLoad_2_oe",
"JCSCacheElementRetrievalUnitTest.testSimpleElementRetrieval_1_oe",
"JCSCacheElementRetrievalUnitTest.testSimpleElementRetrieval_2_oe",
"JCSLightLoadUnitTest.setUp",
"JCSRemovalSimpleConcurrentTest.setUp",
"JCSThrashTest.setUp",
"ZeroSizeCacheUnitTest.setUp",
"AdminBeanUnitTest.testClearAll_1_oe",
"AdminBeanUnitTest.testGetElementForRegionInfo_1_oe",
"AdminBeanUnitTest.testGetElementForRegionInfo_2_oe",
"AdminBeanUnitTest.testGetRegionInfo_1_oe",
"AdminBeanUnitTest.testGetRegionInfo_2_oe",
"AdminBeanUnitTest.testGetRegionInfo_3_oe",
"AdminBeanUnitTest.testRemove_1_oe",
"AdminBeanUnitTest.testRemove_2_oe",
"AdminBeanUnitTest.testRemove_3_oe",
"AuxiliaryCacheConfiguratorUnitTest.testParseCacheEventLogger_Normal_1_oe",
"AuxiliaryCacheConfiguratorUnitTest.testParseCacheEventLogger_Normal_2_oe",
"AuxiliaryCacheConfiguratorUnitTest.testParseCacheEventLogger_NullName_1_oe",
"AuxiliaryCacheConfiguratorUnitTest.testParseCacheEventLogger_Null_1_oe",
"AuxiliaryCacheConfiguratorUnitTest.testParseElementSerializer_Normal_1_oe",
"AuxiliaryCacheConfiguratorUnitTest.testParseElementSerializer_Normal_2_oe",
"AuxiliaryCacheConfiguratorUnitTest.testParseElementSerializer_Null_1_oe",
"BlockDiskCacheKeyStoreUnitTest.testObjectLargerThanMaxSize_1_oe",
"BlockDiskCacheKeyStoreUnitTest.testObjectLargerThanMaxSize_2_oe",
"BlockDiskUnitTest.testCalculateBlocksNeededDouble_1_oe",
"BlockDiskUnitTest.testJCS156_1_oe",
"BlockDiskUnitTest.testJCS156_2_oe",
"BlockDiskUnitTest.testWriteAndReadMultipleMultiBlockElement_1_oe",
"BlockDiskUnitTest.testWriteAndReadMultipleMultiBlockElement_2_oe",
"BlockDiskUnitTest.testWriteAndReadMultipleMultiBlockElement_setSize_1_oe",
"BlockDiskUnitTest.testWriteAndReadMultipleMultiBlockElement_setSize_2_oe",
"BlockDiskUnitTest.testWriteAndReadMultipleMultiBlockElement_setSize_3_oe",
"BlockDiskUnitTest.testWriteAndReadMultipleMultiBlockElement_setSize_4_oe",
"BlockDiskUnitTest.testWriteAndRead_BigString2_1_oe",
"BlockDiskUnitTest.testWriteAndRead_BigString_1_oe",
"BlockDiskUnitTest.testWriteAndRead_SingleBlockElement_1_oe",
"BlockDiskUnitTest.testWrite_128BlockElement_1_oe",
"BlockDiskUnitTest.testWrite_128BlockElement_2_oe",
"BlockDiskUnitTest.testWrite_128BlockElement_3_oe",
"BlockDiskUnitTest.testWrite_DoubleBlockElement_1_oe",
"BlockDiskUnitTest.testWrite_DoubleBlockElement_2_oe",
"BlockDiskUnitTest.testWrite_DoubleBlockElement_3_oe",
"BlockDiskUnitTest.testWrite_NullBlockElement_1_oe",
"BlockDiskUnitTest.testWrite_NullBlockElement_2_oe",
"BlockDiskUnitTest.testWrite_NullBlockElement_3_oe",
"BlockDiskUnitTest.testWrite_SingleBlockElement_1_oe",
"BlockDiskUnitTest.testWrite_SingleBlockElement_2_oe",
"BlockDiskUnitTest.testWrite_SingleBlockElement_3_oe",
"BlockDiskUnitTest.testWrite_TwoSingleBlockElements_1_oe",
"BlockDiskUnitTest.testWrite_TwoSingleBlockElements_2_oe",
"BlockDiskUnitTest.testWrite_TwoSingleBlockElements_3_oe",
"BlockDiskUnitTest.testWrite_TwoSingleBlockElements_4_oe",
"BlockDiskUnitTest.testWrite_TwoSingleBlockElements_5_oe",
"IndexedDiskCacheOptimizationUnitTest.testBasicOptimization_1_oe",
"IndexedDiskCacheOptimizationUnitTest.testBasicOptimization_2_oe",
"LRUMapSizeVsCount.testSimpleLoad_1_oe",
"LRUMapSizeVsCount.testSimpleLoad_2_oe",
"LateralTCPIssueRemoveOnPutUnitTest.testStandardPut_1_oe",
"RemoteCacheListenerUnitTest.testUpdate_PutOnPut_1_oe",
"RemoteCacheListenerUnitTest.testUpdate_PutOnPut_2_oe",
"RemoteCacheListenerUnitTest.testUpdate_PutOnPut_3_oe",
"RemoteCacheListenerUnitTest.testUpdate_PutOnPut_4_oe",
"RemoteCacheListenerUnitTest.testUpdate_PutOnPut_5_oe",
"RemoteCacheListenerUnitTest.testUpdate_RemoveOnPut_1_oe",
"RemoteCacheNoWaitFacadeUnitTest.testAddNoWait_InList_1_oe",
"RemoteCacheNoWaitFacadeUnitTest.testAddNoWait_InList_2_oe",
"RemoteCacheNoWaitFacadeUnitTest.testAddNoWait_InList_3_oe",
"RemoteCacheNoWaitFacadeUnitTest.testFailover_10_oe",
"RemoteCacheNoWaitFacadeUnitTest.testFailover_1_oe",
"RemoteCacheNoWaitFacadeUnitTest.testFailover_2_oe",
"RemoteCacheNoWaitFacadeUnitTest.testFailover_3_oe",
"RemoteCacheNoWaitFacadeUnitTest.testFailover_4_oe",
"RemoteCacheNoWaitFacadeUnitTest.testFailover_5_oe",
"RemoteCacheNoWaitFacadeUnitTest.testFailover_6_oe",
"RemoteCacheNoWaitFacadeUnitTest.testFailover_7_oe",
"RemoteCacheNoWaitFacadeUnitTest.testFailover_8_oe",
"RemoteCacheNoWaitFacadeUnitTest.testFailover_9_oe",
"RemoteCacheNoWaitUnitTest.testFixCache_1_oe",
"RemoteCacheNoWaitUnitTest.testGetMultiple_1_oe",
"RemoteCacheNoWaitUnitTest.testGetStats_1_oe",
"RemoteCacheNoWaitUnitTest.testGetStatus_error_1_oe",
"RemoteCacheNoWaitUnitTest.testGet_1_oe",
"RemoteCacheNoWaitUnitTest.testRemove_1_oe",
"RemoteCacheNoWaitUnitTest.testRemove_2_oe",
"RemoteCacheNoWaitUnitTest.testUpdate_1_oe",
"RemoteCacheNoWaitUnitTest.testUpdate_2_oe",
"RemoteCacheUnitTest.testDispose_nullListener_1_oe",
"RemoteCacheUnitTest.testDispose_nullListener_2_oe",
"RemoteCacheUnitTest.testDispose_simple_1_oe",
"RemoteCacheUnitTest.testDispose_simple_2_oe",
"RemoteCacheUnitTest.testGetMatching_simple_1_oe",
"RemoteCacheUnitTest.testGetMatching_simple_2_oe",
"RemoteCacheUnitTest.testGetMatching_simple_3_oe",
"RemoteCacheUnitTest.testGetMultiple_simple_1_oe",
"RemoteCacheUnitTest.testGetMultiple_simple_2_oe",
"RemoteCacheUnitTest.testGet_simple_1_oe",
"RemoteCacheUnitTest.testGet_simple_2_oe",
"RemoteCacheUnitTest.testRemoveAll_simple_1_oe",
"RemoteCacheUnitTest.testRemoveAll_simple_2_oe",
"RemoteCacheUnitTest.testRemove_simple_1_oe",
"RemoteCacheUnitTest.testRemove_simple_2_oe",
"RemoteCacheUnitTest.testUpdateZombieThenFix_1_oe",
"RemoteCacheUnitTest.testUpdateZombieThenFix_2_oe",
"RemoteCacheUnitTest.testUpdate_1_oe",
"RemoteCacheUnitTest.testUpdate_2_oe",
"RemoteCacheUnitTest.testUpdate_3_oe",
"RemoteCacheUnitTest.testUpdate_simple_1_oe",
"RemoteCacheUnitTest.testUpdate_simple_2_oe",
"RemoteUtilsUnitTest.testCreateRegistry_1_oe",
"RemoteUtilsUnitTest.testParseServerAndPort_5_oe",
"ZombieRemoteCacheServiceUnitTest.testRemoveAllThenWalk_1_oe",
"ZombieRemoteCacheServiceUnitTest.testRemoveThenWalk_1_oe",
"ZombieRemoteCacheServiceUnitTest.testUpdateThenWalk_1_oe",
"ZombieRemoteCacheServiceUnitTest.testUpdateThenWalk_zeroSize_1_oe",
"RemoteHttpCacheClientUnitTest.testDispose_normal_1_oe",
"RemoteHttpCacheClientUnitTest.testGetMatching_normal_1_oe",
"RemoteHttpCacheClientUnitTest.testGetMatching_normal_2_oe",
"RemoteHttpCacheClientUnitTest.testGetMultiple_normal_1_oe",
"RemoteHttpCacheClientUnitTest.testGetMultiple_normal_2_oe",
"RemoteHttpCacheClientUnitTest.testGet_normal_1_oe",
"RemoteHttpCacheClientUnitTest.testGet_normal_2_oe",
"RemoteHttpCacheClientUnitTest.testGet_nullFromDispatcher_1_oe",
"RemoteHttpCacheClientUnitTest.testGet_nullFromDispatcher_2_oe",
"RemoteHttpCacheClientUnitTest.testRemoveAll_normal_1_oe",
"RemoteHttpCacheClientUnitTest.testRemove_normal_1_oe",
"RemoteHttpCacheClientUnitTest.testUpdate_normal_1_oe",
"RemoteHttpCacheFactoryUnitTest.testCreateRemoteHttpCacheClient_Bad_1_oe",
"RemoteHttpCacheFactoryUnitTest.testCreateRemoteHttpCacheClient_Bad_2_oe",
"RemoteHttpCacheFactoryUnitTest.testCreateRemoteHttpCacheClient_Bad_3_oe",
"RemoteHttpCacheFactoryUnitTest.testCreateRemoteHttpCacheClient_default_1_oe",
"RemoteHttpCacheFactoryUnitTest.testCreateRemoteHttpCacheClient_default_2_oe",
"RemoteHttpCacheFactoryUnitTest.testGetCache_normal_4_oe",
"RemoteHttpCacheManualTester.testSimpleLoad_1_oe",
"RemoteHttpCacheServiceUnitTest.testGetMatching_simple_1_oe",
"RemoteHttpCacheServiceUnitTest.testGetMatching_simple_2_oe",
"RemoteHttpCacheServiceUnitTest.testGetMultiple_simple_1_oe",
"RemoteHttpCacheServiceUnitTest.testGetMultiple_simple_2_oe",
"RemoteHttpCacheServiceUnitTest.testGet_simple_1_oe",
"JCSLightLoadUnitTest.testSimpleLoad_1_oe",
"JCSThrashTest.testForMemoryLeaks_1_oe",
"JCSThrashTest.testPut_3_oe",
"JCSThrashTest.testRemove_1_oe",
"JCSThrashTest.testRemove_3_oe",
"JCSThrashTest.testRemove_4_oe",
"JCSThrashTest.testRemove_5_oe",
"ZeroSizeCacheUnitTest.testPutGetRemove_1_oe",
"ZeroSizeCacheUnitTest.testPutGetRemove_2_oe",
"SoftReferenceMemoryCacheUnitTest.testPutGetThroughHub_2_oe",
"SerializerUnitTest.testReadWrite_2_oe",
"SoftReferenceMemoryCacheUnitTest.testPutGetThroughHub_3_oe",
"SoftReferenceMemoryCacheUnitTest.testLoadFromCCF_1_oe",
"SerializerUnitTest.testReadWrite_3_oe",
"GroupCacheAccessUnitTest.testGroupCache_10_oe",
"GroupCacheAccessUnitTest.testGroupCache_11_oe",
"GroupCacheAccessUnitTest.testGroupCache_6_oe",
"GroupCacheAccessUnitTest.testGroupCache_8_oe",
"GroupCacheAccessUnitTest.testGroupCache_9_oe",
"GroupCacheAccessUnitTest.testInvalidate_2_oe",
"GroupCacheAccessUnitTest.testInvalidate_3_oe",
"GroupCacheAccessUnitTest.testInvalidate_5_oe",
"GroupCacheAccessUnitTest.testRemove_2_oe",
"SystemPropertyUnitTest.test2SystemPropertyMissingInValueDelimeter_1_oe",
"JDBCDiskCacheShrinkUnitTest.testDidNotExpireEternal_1_oe",
"JDBCDiskCacheShrinkUnitTest.testDidNotExpire_1_oe",
"HSQLDiskCacheUnitTest.testBasicPutRemove_1_oe",
"HSQLDiskCacheUnitTest.testBasicPutRemove_2_oe",
"MySQLDiskCacheHsqlBackedUnitTest.testPutGetMatchingWithHSQL_1_oe",
"HSQLDiskCacheUnitTest.testBasicPutRemove_3_oe",
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
"ImmediateExpiryTest.immediate_2_oe",
"NotSerializableTest.run_1_oe",
"NotSerializableTest.run_2_oe",
"NotSerializableTest.run_3_oe",
"NotSerializableTest.run_4_oe",

"UDPDiscoverySenderUnitTest.*",


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
