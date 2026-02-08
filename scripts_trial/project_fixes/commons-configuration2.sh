cd "$PWD/projects_decomposed/commons-configuration2"

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [
    "TestConstantLookup.testLookupConstant_1_oe",
    "TestPropertiesConfiguration.testReadCalledDirectly_1_oe",
    "TestXMLPropertyListConfiguration.testWriteCalledDirectly_1_oe",
    "TestBaseConfiguration.testAddProperty*",
        "TestAbstractConfigurationBasicFeatures.testGetCollection_2_oe",
    "TestAbstractHierarchicalConfiguration.testGetKeysAttributePrefix_2_oe",
    "TestAbstractHierarchicalConfiguration.testGetKeysOrder_2_oe",
    "TestAbstractHierarchicalConfiguration.testGetKeysOrder_3_oe",
    "TestAbstractHierarchicalConfiguration.testGetKeysWithKeyAsPrefixMultiple_2_oe",
    "TestAbstractHierarchicalConfiguration.testGetKeysWithKeyAsPrefixMultiple_3_oe",
    "TestAbstractHierarchicalConfiguration.testGetKeysWithKeyAsPrefixMultiple_4_oe",
    "TestAbstractHierarchicalConfiguration.testGetKeysWithKeyAsPrefix_3_oe",
    "TestConfigurationUtils.testConvertHierarchicalToHierarchicalEngine_2_oe",
    "TestDataConfiguration.testGetKeys_3_oe",
    "TestDatabaseConfiguration.tearDown",
    "TestDatabaseConfiguration.testGetKeysMultiple_2_oe",
    "TestDatabaseConfiguration.testGetKeysSingle_2_oe",
    "TestDynamicCombinedConfiguration.testConcurrentGetAndReloadFile_3_oe",
    "TestDynamicCombinedConfiguration.testConcurrentGetAndReloadFile_4_oe",
    "TestDynamicCombinedConfiguration.testConcurrentGetAndReloadFile_6_oe",
    "TestDynamicCombinedConfiguration.testConcurrentGetAndReloadMultipleClients_2_oe",
    "TestHierarchicalXMLConfiguration.testSetRootElementNameWhenLoadedFromFile_1_oe",
    "TestINIConfiguration.testGetSectionDuplicate_2_oe",
    "TestINIConfiguration.testKeysOfGlobalSection_2_oe",
    "TestJNDIConfiguration.tearDown",
    "TestPropertiesConfigurationLayout.testInit_5_oe",
    "TestSubnodeConfiguration.testSetThrowExceptionOnMissing_1_oe",
    "TestSubsetConfiguration.testGetKeysWithPrefix_2_oe",
    "TestSubsetConfiguration.testGetKeysWithPrefix_3_oe",
    "TestSubsetConfiguration.testGetKeys_2_oe",
    "TestSubsetConfiguration.testGetKeys_3_oe",
    "TestBeanHelper.testDeregisterBeanFactory_2_oe",
    "TestConfigurationDynaBean.testGetDescriptorArguments_1_oe",
    "TestBasicBuilderParameters.testSetBeanHelper_2_oe",
    "TestBasicBuilderParameters.testSetConfigurationDecoder_2_oe",
    "TestBasicBuilderParameters.testSetConversionHandler_2_oe",
    "TestBasicBuilderParameters.testSetDefaultLookups_5_oe",
    "TestBasicBuilderParameters.testSetInterpolator_2_oe",
    "TestBasicBuilderParameters.testSetListDelimiter_2_oe",
    "TestBasicBuilderParameters.testSetLogger_2_oe",
    "TestBasicBuilderParameters.testSetParentInterpolator_2_oe",
    "TestBasicBuilderParameters.testSetPrefixLookups_5_oe",
    "TestBasicBuilderParameters.testSetSynchronizer_2_oe",
    "TestBasicBuilderParameters.testSetThrowExceptionOnMissing_2_oe",
    "TestBasicConfigurationBuilder.testAddParameters_3_oe",
    "TestBasicConfigurationBuilder.testConnectToReloadingController_3_oe",
    "TestBasicConfigurationBuilder.testRemoveConfigurationListener_2_oe",
    "TestBasicConfigurationBuilder.testSetParameters_2_oe",
    "TestBuilderConfigurationWrapperFactory.testEventSourceSupportBuilder_2_oe",
    "TestBuilderConfigurationWrapperFactory.testEventSourceSupportBuilder_4_oe",
    "TestDatabaseBuilderParametersImpl.testSetAutoCommit_2_oe",
    "TestDatabaseBuilderParametersImpl.testSetConfigurationNameColumn_2_oe",
    "TestDatabaseBuilderParametersImpl.testSetConfigurationName_2_oe",
    "TestDatabaseBuilderParametersImpl.testSetDataSource_2_oe",
    "TestDatabaseBuilderParametersImpl.testSetKeyColumn_2_oe",
    "TestDatabaseBuilderParametersImpl.testSetTable_2_oe",
    "TestDatabaseBuilderParametersImpl.testSetValueColumn_2_oe",
    "TestEventListenerParameters.testAddEventListenerRegistration_2_oe",
    "TestEventListenerParameters.testAddEventListener_2_oe",
    "TestFileBasedBuilderParameters.testSetBasePath_2_oe",
    "TestFileBasedBuilderParameters.testSetEncoding_2_oe",
    "TestFileBasedBuilderParameters.testSetFileName_2_oe",
    "TestFileBasedBuilderParameters.testSetFileSystem_2_oe",
    "TestFileBasedBuilderParameters.testSetFile_2_oe",
    "TestFileBasedBuilderParameters.testSetLocationStrategy_2_oe",
    "TestFileBasedBuilderParameters.testSetPath_2_oe",
    "TestFileBasedBuilderParameters.testSetReloadingDetectorFactory_3_oe",
    "TestFileBasedBuilderParameters.testSetReloadingRefreshDelay_2_oe",
    "TestHierarchicalBuilderParametersImpl.testSetExpressionEngine_2_oe",
    "TestJndiBuilderParametersImpl.testSetContext_2_oe",
    "TestJndiBuilderParametersImpl.testSetPrefix_2_oe",
    "TestPropertiesBuilderParametersImpl.testSetIOFactory_2_oe",
    "TestPropertiesBuilderParametersImpl.testSetIncludeListener_2_oe",
    "TestPropertiesBuilderParametersImpl.testSetIncludesAllowed_2_oe",
    "TestPropertiesBuilderParametersImpl.testSetLayout_2_oe",
    "TestReloadingFileBasedConfigurationBuilder.testReloadingDetectorIsReloadingRequired_2_oe",
    "TestXMLBuilderParametersImpl.testSetDocumentBuilder_2_oe",
    "TestXMLBuilderParametersImpl.testSetEntityResolver_2_oe",
    "TestXMLBuilderParametersImpl.testSetEntityResolver_3_oe",
    "TestXMLBuilderParametersImpl.testSetPublicID_2_oe",
    "TestXMLBuilderParametersImpl.testSetSchemaValidation_2_oe",
    "TestXMLBuilderParametersImpl.testSetSystemID_2_oe",
    "TestXMLBuilderParametersImpl.testSetValidating_2_oe",
    "TestCombinedBuilderParametersImpl.testGetChildDefaultParametersManagerSpecific_2_oe",
    "TestCombinedBuilderParametersImpl.testRegisterMissingProvidersParams_2_oe",
    "TestCombinedBuilderParametersImpl.testRegisterMissingProvidersParams_4_oe",
    "TestCombinedBuilderParametersImpl.testRegisterMissingProviders_2_oe",
    "TestCombinedBuilderParametersImpl.testRegisterMissingProviders_4_oe",
    "TestCombinedBuilderParametersImpl.testRegisterProvider_2_oe",
    "TestCombinedBuilderParametersImpl.testRegisterProvider_3_oe",
    "TestCombinedBuilderParametersImpl.testRegisterProvider_4_oe",
    "TestCombinedBuilderParametersImpl.testSetBasePath_2_oe",
    "TestCombinedBuilderParametersImpl.testSetDefinitionBuilderParameters_2_oe",
    "TestCombinedBuilderParametersImpl.testSetDefinitionBuilder_3_oe",
    "TestCombinedBuilderParametersImpl.testSetInheritSettings_3_oe",
    "TestCombinedConfigurationBuilder.testCustomResultConfiguration_1_oe",
    "TestCombinedConfigurationBuilder.testMultiTenentConfigurationReloading_5_oe",
    "TestMultiFileBuilderParametersImpl.testSetFilePattern_2_oe",
    "TestMultiFileBuilderParametersImpl.testSetManagedBuilderParameters_2_oe",
    "TestMultiFileConfigurationBuilder.testAddConfigurationListener_4_oe",
    "TestMultiFileConfigurationBuilder.testAddConfigurationListener_5_oe",
    "TestMultiFileConfigurationBuilder.testAddConfigurationListener_6_oe",
    "TestReloadingCombinedConfigurationBuilderFileBased.testConcurrentGetAndReload_2_oe",
    "TestDisabledListDelimiterHandler.testFlattenCollectionWithArrayWithLimit_3_oe",
    "TestDisabledListDelimiterHandler.testParseSimpleValue_2_oe",
    "TestEventListenerList.testEventListenerIteratorWrongEvent_1_oe",
    "TestEventListenerList.testGetEventListenerRegistrationsForSuperType_2_oe",
    "TestEventListenerList.testGetEventListenerRegistrationsForSuperType_3_oe",
    "TestEventListenerList.testGetEventListenersIteratorRemove_1_oe",
    "TestEventSource.testFireError_2_oe",
    "TestEventSource.testFireError_3_oe",
    "TestEventSource.testRemoveEventListener_4_oe",
    "TestConfigurationInterpolator.testDeregisterLookup_2_oe",
    "TestConfigurationInterpolator.testDeregisterLookup_3_oe",
    "TestConfigurationInterpolator.testRemoveDefaultLookup_2_oe",
    "TestConfigurationInterpolator.testRemoveDefaultLookup_3_oe",
    "TestFileHandler.testLocateSuccess_2_oe",
    "TestFileHandler.testLocateSuccess_3_oe",
    "TestFileLocatorUtils.testDefaultFileLocationStrategy_2_oe",
    "TestFileLocatorUtils.testDefaultFileLocationStrategy_3_oe",
    "TestFileLocatorUtils.testDefaultFileLocationStrategy_4_oe",
    "TestFileLocatorUtils.testDefaultFileLocationStrategy_5_oe",
    "TestFileLocatorUtils.testDefaultFileLocationStrategy_7_oe",
    "TestFileLocatorUtils.testDefaultFileLocationStrategy_9_oe",
    "TestFileHandlerReloadingDetector.testIsReloadingRequiredTrue_2_oe",
    "TestFileHandlerReloadingDetector.testRefreshReloadingAndReset_2_oe",
    "TestFileHandlerReloadingDetector.testRefreshReloadingAndReset_3_oe",
    "TestFileHandlerReloadingDetector.testReloadingAndReset_2_oe",
    "TestFileHandlerReloadingDetector.testReloadingAndReset_3_oe",
    "TestReloadingController.testCheckForReloadingTrue_3_oe",
    "TestDefaultConfigurationKey.testIterateAlternativeEscapeDelimiter_3_oe",
    "TestDefaultConfigurationKey.testIterateAlternativeEscapeDelimiter_4_oe",
    "TestDefaultConfigurationKey.testIterateAlternativeEscapeDelimiter_5_oe",
    "TestDefaultConfigurationKey.testIterateAttributeEqualsPropertyDelimiter_10_oe",
    "TestDefaultConfigurationKey.testIterateAttributeEqualsPropertyDelimiter_4_oe",
    "TestDefaultConfigurationKey.testIterateAttributeEqualsPropertyDelimiter_7_oe",
    "TestDefaultConfigurationKey.testIterateAttributeEqualsPropertyDelimiter_8_oe",
    "TestDefaultConfigurationKey.testIterateEscapedDelimiters_3_oe",
    "TestDefaultConfigurationKey.testIterateEscapedDelimiters_4_oe",
    "TestDefaultConfigurationKey.testIterateEscapedDelimiters_5_oe",
    "TestDefaultConfigurationKey.testIterateStrangeKeys_3_oe",
    "TestDefaultConfigurationKey.testIterateStrangeKeys_7_oe",
    "TestDefaultConfigurationKey.testIterateStrangeKeys_8_oe",
    "TestDefaultConfigurationKey.testIterateWithRemove_1_oe",
    "TestDefaultConfigurationKey.testIterateWithRemove_2_oe",
    "TestDefaultConfigurationKey.testIterateWithRemove_3_oe",
    "TestDefaultConfigurationKey.testIterateWithoutEscapeDelimiter_3_oe",
    "TestDefaultConfigurationKey.testIterate_11_oe",
    "TestDefaultConfigurationKey.testIterate_12_oe",
    "TestDefaultConfigurationKey.testIterate_13_oe",
    "TestDefaultConfigurationKey.testIterate_14_oe",
    "TestDefaultConfigurationKey.testIterate_15_oe",
    "TestDefaultConfigurationKey.testIterate_3_oe",
    "TestDefaultConfigurationKey.testIterate_4_oe",
    "TestDefaultConfigurationKey.testIterate_6_oe",
    "TestDefaultConfigurationKey.testIterate_8_oe",
    "TestDefaultConfigurationKey.testIterate_9_oe",
    "TestInMemoryNodeModelTrackedNodes.testTrackChildNodes_2_oe",
    "TestInMemoryNodeModelTrackedNodes.testTrackChildNodes_3_oe",
    "TestInMemoryNodeModel.testAddPropertyWithPathNodes_3_oe",
    "TestConfigurationNodeIteratorChildren.testIterateStartsWith_3_oe",
    "TestBasicBuilderParameters.testSetDefaultLookups_3_oe",
    "TestBasicBuilderParameters.testSetDefaultLookups_4_oe",
    "TestBasicBuilderParameters.testSetPrefixLookups_3_oe",
    "TestBasicBuilderParameters.testSetPrefixLookups_4_oe",
    "TestEventListenerParameters.testAddEventListenerRegistration_3_oe",
    "TestEventListenerParameters.testAddEventListener_3_oe",
    "TestEventListenerParameters.testAddEventListener_4_oe",
    "TestFileBasedBuilderParameters.testSetURL_2_oe",
    "TestCombinedConfigurationBuilder.testCustomEntityResolver_1_oe",
    "TestFileHandler.testLocateSuccess_5_oe",
    "TestFileLocatorUtils.testDefaultFileLocationStrategy_6_oe",
    "TestFileLocatorUtils.testDefaultFileLocationStrategy_8_oe",
    "TestHomeDirectoryLocationStrategy.testLocateSuccessInSubFolder_2_oe",
    "TestReloadingController.testCheckForReloadingTrue_4_oe",
    "TestReloadingController.testCheckForReloadingTrue_5_oe",
    "TestReloadingController.testCheckForReloadingTrue_6_oe",
    "TestInMemoryNodeModel.testConcurrentUpdate_2_oe",
    "TestInMemoryNodeModel.testConcurrentUpdate_3_oe",
    "TestEventListenerList.testReceiveEventDifferentType_2_oe_1_oe",
    "TestEventListenerList.testRemoveEventListenerExisting_2_oe_1_oe",
    "TestEventListenerList.testSuppressEventOfSuperType_1_oe_1_oe",
    "TestMultiFileConfigurationBuilder.testBuilderListenerOtherTypes_3_oe_1_oe",
    "TestMultiFileConfigurationBuilder.testRemoveBuilderListenerOnReset_1_oe_1_oe",
    "TestBasicConfigurationBuilderEvents.testBuilderResetEvent_3_oe_1_oe",
    "TestBasicConfigurationBuilderEvents.testConfigurationRequestEvent_2_oe_1_oe",
    "TestBasicConfigurationBuilderEvents.testRemoveEventListener_2_oe_1_oe",
    "TestBasicConfigurationBuilderEvents.testResetOnConfigurationRequestEvent_2_oe_1_oe",
    "TestReloadingBuilderSupportListener.testResetBuilderOnReloadingEvent_2_oe_1_oe",
    "TestReloadingFileBasedConfigurationBuilder.testReloadingControllerEvents_1_oe_1_oe",
    "TestCombinedConfigurationBuilder.testMultiTenentConfigurationReloading_7_oe",
"TestReloadingController.testCheckForReloadingTrue_2_oe",
"TestDatabaseConfiguration.testContainsKeyError_1_oe",
"TestDatabaseConfiguration.testIsEmptyError_1_oe",
"TestJNDIConfiguration.testContainsKeyError_1_oe",
"TestJNDIConfiguration.testGetPropertyError_1_oe",
"TestJNDIConfiguration.testGetKeysError_1_oe",
"TestJNDIConfiguration.testIsEmptyError_1_oe",
"TestCombinedConfigurationBuilder.testMultiTenentConfigurationReloading_7_oe",
"TestDynamicCombinedConfiguration.testConcurrentGetAndReload2_3_oe",
]

patterns = []
for full in fail_list:
    cls, name = full.split(".", 1)
    if name.endswith("*"):
        name = name[:-1]
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
