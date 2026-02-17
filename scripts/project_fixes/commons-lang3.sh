cd "$PWD/projects_decomposed/commons-lang3"
echo $PWD

find . -type f -name "*.csv" -print0 \
| xargs -0 sed -i.bak 's/iterator0(/iterator(/g'

python3 - << 'PY'
from pathlib import Path
import csv

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

fail_list = [

"SerializationUtilsTest.testSerializeIOException_2_oe",
"SerializationUtilsTest.testDeserializeStreamClassNotFound_2_oe",

"EqualsBuilderTest.testReflectionHierarchyEquals*",
"CharRangeTest.testContainsNullArg_2_oe",
"StreamsTest.testSimpleStreamMapFailing_2_oe",
"StreamsTest.testSimpleStreamMapFailing_1_oe",

"MultiBackgroundInitializerTest.testInitializeRuntimeEx_2_oe",
"ConcurrentUtilsTest.testExtractCauseError_2_oe",
"ConcurrentUtilsTest.testExtractCauseUncheckedError_2_oe",
"ConcurrentUtilsTest.testExtractCauseUncheckedUncheckedException_2_oe",
"ConcurrentUtilsTest.testHandleCauseError_2_oe",
"ConcurrentUtilsTest.testHandleCauseUncheckedException_2_oe",
"ConcurrentUtilsTest.testHandleCauseUncheckedChecked_2_oe",
"ConcurrentUtilsTest.testHandleCauseChecked_2_oe",
"ConcurrentUtilsTest.testHandleCauseUncheckedError_2_oe",
"ConcurrentUtilsTest.testHandleCauseUncheckedUncheckedException_2_oe",
"ConcurrentUtilsTest.testInitializeUncheckedEx_2_oe",
"ConcurrentUtilsTest.testCreateIfAbsentUncheckedException_2_oe",
"BackgroundInitializerTest.testGetRuntimeException_2_oe",
"BackgroundInitializerTest.testGetCheckedException_2_oe",
"ArrayUtilsTest.testIndirectEmptyArrayCreation_1_oe",

"ObjectUtilsTest.testCloneOfUncloneable_2_oe",
"ObjectUtilsTest.testPossibleCloneOfUncloneable_2_oe",
"ExceptionUtilsTest.testCatchTechniques*",
"ExceptionUtilsTest.testThrow_2_oe",
"ExceptionUtilsTest.testWrapAndUnwrapCheckedException_2_oe",
"ExceptionUtilsTest.testWrapAndUnwrapError_2_oe",
"ExceptionUtilsTest.testWrapAndUnwrapRuntimeException_2_oe",
"ExceptionUtilsTest.testWrapAndUnwrapThrowable_2_oe",
"EventUtilsTest.testAddEventListenerWithNoAddMethod_2_oe",
"EventUtilsTest.testAddEventListenerWithPrivateAddMethod_2_oe",

"StreamsTest.testSimpleStreamMapFailing_2_oe",

"StreamsTest.simpleStreamFilterFailing_1_oe_1_oe",
"StreamsTest.simpleStreamFilterFailing_1_oe_2_oe",

"DateUtilsTest.testCeil_31_oe",
"DateUtilsTest.testCeil_32_oe",
"DateUtilsTest.testCeil_33_oe",
"DateUtilsTest.testCeil_34_oe",
"DateUtilsTest.testCeil_35_oe",
"DateUtilsTest.testCeil_36_oe",
"DateUtilsTest.testCeil_37_oe",
"DateUtilsTest.testCeil_38_oe",
"DateUtilsTest.testCeil_39_oe",
"DateUtilsTest.testCeil_3_oe",
"DateUtilsTest.testCeil_40_oe",
"DateUtilsTest.testCeil_41_oe",
"DateUtilsTest.testCeil_42_oe",
"DateUtilsTest.testCeil_43_oe",
"DateUtilsTest.testCeil_44_oe",
"DateUtilsTest.testCeil_4_oe",
"DateUtilsTest.testCeil_5_oe",
"DateUtilsTest.testCeil_6_oe",
"DateUtilsTest.testCeil_7_oe",
"DateUtilsTest.testCeil_8_oe",
"DateUtilsTest.testCeil_9_oe",

"DateUtilsTest.testRoundLang346_1_oe",
"DateUtilsTest.testRoundLang346_2_oe",
"DateUtilsTest.testRoundLang346_3_oe",
"DateUtilsTest.testRoundLang346_4_oe",
"DateUtilsTest.testRoundLang346_5_oe",
"DateUtilsTest.testRoundLang346_6_oe",
"DateUtilsTest.testRoundLang346_7_oe",
"DateUtilsTest.testRoundLang346_8_oe",

"DateUtilsTest.testRound_1_oe",
"DateUtilsTest.testRound_2_oe",
"DateUtilsTest.testRound_3_oe",
"DateUtilsTest.testRound_4_oe",
"DateUtilsTest.testRound_5_oe",
"DateUtilsTest.testRound_6_oe",
"DateUtilsTest.testRound_7_oe",
"DateUtilsTest.testRound_8_oe",
"DateUtilsTest.testRound_9_oe",
"DateUtilsTest.testRound_10_oe",
"DateUtilsTest.testRound_11_oe",
"DateUtilsTest.testRound_12_oe",
"DateUtilsTest.testRound_13_oe",
"DateUtilsTest.testRound_14_oe",
"DateUtilsTest.testRound_15_oe",
"DateUtilsTest.testRound_16_oe",
"DateUtilsTest.testRound_17_oe",
"DateUtilsTest.testRound_18_oe",
"DateUtilsTest.testRound_19_oe",
"DateUtilsTest.testRound_20_oe",
"DateUtilsTest.testRound_21_oe",
"DateUtilsTest.testRound_22_oe",
"DateUtilsTest.testRound_23_oe",
"DateUtilsTest.testRound_24_oe",
"DateUtilsTest.testRound_25_oe",
"DateUtilsTest.testRound_26_oe",
"DateUtilsTest.testRound_27_oe",
"DateUtilsTest.testRound_28_oe",
"DateUtilsTest.testRound_29_oe",
"DateUtilsTest.testRound_30_oe",
"DateUtilsTest.testRound_31_oe",
"DateUtilsTest.testRound_32_oe",
"DateUtilsTest.testRound_33_oe",
"DateUtilsTest.testRound_34_oe",
"DateUtilsTest.testRound_35_oe",
"DateUtilsTest.testRound_36_oe",
"DateUtilsTest.testRound_37_oe",
"DateUtilsTest.testRound_38_oe",
"DateUtilsTest.testRound_39_oe",
"DateUtilsTest.testRound_45_oe",
"DateUtilsTest.testRound_46_oe",
"DateUtilsTest.testRound_47_oe",
"DateUtilsTest.testRound_48_oe",

"DateUtilsTest.testTruncate_1_oe",
"DateUtilsTest.testTruncate_2_oe",
"DateUtilsTest.testTruncate_3_oe",
"DateUtilsTest.testTruncate_4_oe",
"DateUtilsTest.testTruncate_5_oe",
"DateUtilsTest.testTruncate_6_oe",
"DateUtilsTest.testTruncate_7_oe",
"DateUtilsTest.testTruncate_8_oe",
"DateUtilsTest.testTruncate_9_oe",
"DateUtilsTest.testTruncate_10_oe",
"DateUtilsTest.testTruncate_11_oe",
"DateUtilsTest.testTruncate_12_oe",
"DateUtilsTest.testTruncate_13_oe",
"DateUtilsTest.testTruncate_14_oe",
"DateUtilsTest.testTruncate_15_oe",
"DateUtilsTest.testTruncate_16_oe",
"DateUtilsTest.testTruncate_17_oe",
"DateUtilsTest.testTruncate_18_oe",
"DateUtilsTest.testTruncate_19_oe",
"DateUtilsTest.testTruncate_20_oe",
"DateUtilsTest.testTruncate_21_oe",
"DateUtilsTest.testTruncate_22_oe",
"DateUtilsTest.testTruncate_23_oe",
"DateUtilsTest.testTruncate_24_oe",
"DateUtilsTest.testTruncate_25_oe",
"DateUtilsTest.testTruncate_26_oe",
"DateUtilsTest.testTruncate_27_oe",
"DateUtilsTest.testTruncate_28_oe",
"DateUtilsTest.testTruncate_29_oe",
"DateUtilsTest.testTruncate_30_oe",
"DateUtilsTest.testTruncate_31_oe",
"DateUtilsTest.testTruncate_32_oe",
"DateUtilsTest.testTruncate_33_oe",
"DateUtilsTest.testTruncate_34_oe",
"DateUtilsTest.testTruncate_35_oe",
"DateUtilsTest.testTruncate_36_oe",
"DateUtilsTest.testTruncate_37_oe",
"DateUtilsTest.testTruncate_38_oe",
"DateUtilsTest.testTruncate_39_oe",
"DateUtilsTest.testTruncate_40_oe",
"DateUtilsTest.testTruncate_41_oe",
"DateUtilsTest.testTruncate_42_oe",
"StrTokenizerTest.testIteration_10_oe",
"StrTokenizerTest.testIteration_11_oe",
"StrTokenizerTest.testIteration_13_oe",
"StrTokenizerTest.testIteration_14_oe",
"StrTokenizerTest.testIteration_15_oe",
"StrTokenizerTest.testIteration_16_oe",
"StrTokenizerTest.testIteration_17_oe",
"StrTokenizerTest.testIteration_18_oe",
"StrTokenizerTest.testIteration_8_oe",
"StrTokenizerTest.testReset_2_oe",
"StrTokenizerTest.testReset_3_oe",
"StrTokenizerTest.testReset_4_oe",
"StrTokenizerTest.testReset_6_oe",
"StrTokenizerTest.testReset_7_oe",
"StrTokenizerTest.testReset_8_oe",
"StrTokenizerTest.testReset_String_2_oe",
"StrTokenizerTest.testReset_String_3_oe",
"StrTokenizerTest.testReset_charArray_2_oe",
"StrTokenizerTest.testTokenizeSubclassInputChange_2_oe",
"StrTokenizerTest.testTokenizeSubclassOutputChange_2_oe",
"StrTokenizerTest.testTokenizeSubclassOutputChange_3_oe",

"DateFormatUtilsTest.testSMTP_1_oe_1_oe",
"DateFormatUtilsTest.testSMTP_1_oe_2_oe",
"DateFormatUtilsTest.testSMTP_1_oe_3_oe",
"DateFormatUtilsTest.testSMTP_2_oe_1_oe",
"DateFormatUtilsTest.testSMTP_2_oe_2_oe",
"DateFormatUtilsTest.testSMTP_2_oe_3_oe",

"DateUtilsTest.testLANG799_EN_FAIL_1_oe",
"DateUtilsTest.testWeekIterator_7_oe",

"FastDateFormatTest.test_changeDefault_Locale_DateInstance_2_oe",
"FastDateFormatTest.test_changeDefault_Locale_DateInstance_4_oe",
"FastDateFormatTest.test_changeDefault_Locale_DateInstance_5_oe",
"FastDateFormatTest.test_changeDefault_Locale_DateTimeInstance_2_oe",
"FastDateFormatTest.test_changeDefault_Locale_DateTimeInstance_4_oe",
"FastDateFormatTest.test_changeDefault_Locale_DateTimeInstance_5_oe",
"FastDateFormatTest.test_getInstance_String_Locale_1_oe",

"FastDateParser_MoreOrLessTest.testInputHasLessCharacters_2_oe",
"FastDateParser_MoreOrLessTest.testInputHasWrongCharacters_2_oe",
"FastDateParser_MoreOrLessTest.testInputHasWrongDay_2_oe",
"FastDateParser_MoreOrLessTest.testInputHasWrongDay_4_oe",
"FastDateParser_MoreOrLessTest.testInputHasWrongTimeZone_2_oe",
"FastDateParser_MoreOrLessTest.testInputHasWrongTimeZone_4_oe",

"FastDatePrinterTest.testFormat_10_oe",
"FastDatePrinterTest.testFormat_11_oe",
"FastDatePrinterTest.testFormat_12_oe",
"FastDatePrinterTest.testFormat_13_oe",
"FastDatePrinterTest.testFormat_14_oe",
"FastDatePrinterTest.testFormat_15_oe",
"FastDatePrinterTest.testFormat_16_oe",
"FastDatePrinterTest.testFormat_17_oe",
"FastDatePrinterTest.testFormat_18_oe",
"FastDatePrinterTest.testFormat_19_oe",
"FastDatePrinterTest.testFormat_8_oe",
"FastDatePrinterTest.testFormat_9_oe",
"FastDatePrinterTest.testLang538_2_oe",
"FastDatePrinterTest.testTimeZoneAsZ_1_oe",
"FastDatePrinterTest.testTimeZoneAsZ_2_oe",
"FastDatePrinterTest.testTimeZoneAsZ_3_oe",

"AnnotationUtilsTest.lambda$testGeneratedAnnotationEquivalentToRealAnnotation_1_oe$1",
"AnnotationUtilsTest.lambda$testHashCode_1_oe$2",
"AnnotationUtilsTest.lambda$testToString_1_oe$3",

"RegExUtilsTest.testReplaceAll_StringPatternString_16_oe",
"RegExUtilsTest.testReplaceFirst_StringPatternString_13_oe",
"RegExUtilsTest.testReplaceFirst_StringStringString_13_oe",
"RegExUtilsTest.testReplacePattern_StringStringString_13_oe",

"StringUtilsTest.testReplaceAll_StringStringString_13_oe",
"StringUtilsTest.testReplaceFirst_StringStringString_13_oe",
"StringUtilsTest.testReplacePattern_StringStringString_13_oe",

"ToStringBuilderTest.testReflectionHierarchyArrayList_1_oe",
"ToStringBuilderTest.testReflectionHierarchyArrayList_2_oe",

"BackgroundInitializerTest.testInitializeTempExecutor_2_oe",
"MemoizerTest.testDoesRecalculateWhenSetToTrue_2_oe",
"MultiBackgroundInitializerTest.testInitializeNoChildren_2_oe",
"MultiBackgroundInitializerTest.testInitializeNoChildren_3_oe",

"TypeUtilsTest.testParameterizeWithOwner_1_oe",

"StrBuilderTest.testChaining_4_oe",

"DateUtilsTest.testCeil_10_oe",
"DateUtilsTest.testCeil_11_oe",
"DateUtilsTest.testCeil_12_oe",
"DateUtilsTest.testCeil_13_oe",
"DateUtilsTest.testCeil_14_oe",
"DateUtilsTest.testCeil_15_oe",
"DateUtilsTest.testCeil_16_oe",
"DateUtilsTest.testCeil_17_oe",
"DateUtilsTest.testCeil_18_oe",
"DateUtilsTest.testCeil_19_oe",
"DateUtilsTest.testCeil_1_oe",
"DateUtilsTest.testCeil_20_oe",
"DateUtilsTest.testCeil_21_oe",
"DateUtilsTest.testCeil_22_oe",
"DateUtilsTest.testCeil_23_oe",
"DateUtilsTest.testCeil_24_oe",
"DateUtilsTest.testCeil_25_oe",
"DateUtilsTest.testCeil_26_oe",
"DateUtilsTest.testCeil_27_oe",
"DateUtilsTest.testCeil_28_oe",
"DateUtilsTest.testCeil_29_oe",
"DateUtilsTest.testCeil_2_oe",
"DateUtilsTest.testCeil_30_oe",
"TimedSemaphoreTest.testGetAvailablePermits_1_oe",
"ExceptionUtilsTest.test_getMessage_Throwable_3_oe",
"MethodUtilsTest.testInvokeMethod_14_oe",
"MethodUtilsTest.testInvokeStaticMethod_12_oe",
"ExtendedMessageFormatTest.testEqualsHashcode_8_oe",
"StrBuilderTest.testAsReader_11_oe",
"StrBuilderTest.testAsReader_12_oe",
"StrBuilderTest.testAsReader_13_oe",
"StrBuilderTest.testAsReader_15_oe",
"StrBuilderTest.testAsReader_16_oe",
"StrBuilderTest.testAsReader_17_oe",
"StrBuilderTest.testAsReader_19_oe",
"StrBuilderTest.testAsReader_3_oe",
"StrBuilderTest.testAsReader_4_oe",
"StrBuilderTest.testAsReader_5_oe",
"StrBuilderTest.testAsReader_6_oe",
"StrBuilderTest.testAsTokenizer_10_oe",
"StrBuilderTest.testAsTokenizer_18_oe",
"StrBuilderTest.testAsTokenizer_19_oe",
"StrBuilderTest.testAsTokenizer_20_oe",
"StrBuilderTest.testReverse_3_oe",
"StrTokenizerTest.testBasic1_2_oe",
"StrTokenizerTest.testBasic1_3_oe",
"StrTokenizerTest.testBasic1_4_oe",
"StrTokenizerTest.testBasic2_2_oe",
"StrTokenizerTest.testBasic2_3_oe",
"StrTokenizerTest.testBasic2_4_oe",
"StrTokenizerTest.testBasic3_2_oe",
"StrTokenizerTest.testBasic3_3_oe",
"StrTokenizerTest.testBasic3_4_oe",
"StrTokenizerTest.testBasic4_2_oe",
"StrTokenizerTest.testBasic4_3_oe",
"StrTokenizerTest.testBasic4_4_oe",
"StrTokenizerTest.testBasic5_2_oe",
"StrTokenizerTest.testBasic5_3_oe",
"StrTokenizerTest.testBasic5_4_oe",
"StrTokenizerTest.testBasicDelim1_2_oe",
"StrTokenizerTest.testBasicDelim1_3_oe",
"StrTokenizerTest.testBasicDelim1_4_oe",
"StrTokenizerTest.testBasicDelim2_2_oe",
"StrTokenizerTest.testBasicEmpty1_2_oe",
"StrTokenizerTest.testBasicEmpty1_3_oe",
"StrTokenizerTest.testBasicEmpty1_4_oe",
"StrTokenizerTest.testBasicEmpty1_5_oe",
"StrTokenizerTest.testBasicEmpty2_2_oe",
"StrTokenizerTest.testBasicEmpty2_3_oe",
"StrTokenizerTest.testBasicEmpty2_4_oe",
"StrTokenizerTest.testBasicEmpty2_5_oe",
"StrTokenizerTest.testBasicIgnoreTrimmed1_2_oe",
"StrTokenizerTest.testBasicIgnoreTrimmed1_3_oe",
"StrTokenizerTest.testBasicIgnoreTrimmed1_4_oe",
"StrTokenizerTest.testBasicIgnoreTrimmed2_2_oe",
"StrTokenizerTest.testBasicIgnoreTrimmed2_3_oe",
"StrTokenizerTest.testBasicIgnoreTrimmed2_4_oe",
"StrTokenizerTest.testBasicIgnoreTrimmed3_2_oe",
"StrTokenizerTest.testBasicIgnoreTrimmed3_3_oe",
"StrTokenizerTest.testBasicIgnoreTrimmed3_4_oe",
"StrTokenizerTest.testBasicIgnoreTrimmed4_2_oe",
"StrTokenizerTest.testBasicIgnoreTrimmed4_3_oe",
"StrTokenizerTest.testBasicIgnoreTrimmed4_4_oe",
"StrTokenizerTest.testBasicQuoted1_2_oe",
"StrTokenizerTest.testBasicQuoted1_3_oe",
"StrTokenizerTest.testBasicQuoted1_4_oe",
"StrTokenizerTest.testBasicQuoted2_2_oe",
"StrTokenizerTest.testBasicQuoted2_3_oe",
"StrTokenizerTest.testBasicQuoted2_4_oe",
"StrTokenizerTest.testBasicQuoted3_2_oe",
"StrTokenizerTest.testBasicQuoted3_3_oe",
"StrTokenizerTest.testBasicQuoted4_2_oe",
"StrTokenizerTest.testBasicQuoted4_3_oe",
"StrTokenizerTest.testBasicQuoted4_4_oe",
"StrTokenizerTest.testBasicQuoted5_2_oe",
"StrTokenizerTest.testBasicQuoted5_3_oe",
"StrTokenizerTest.testBasicQuoted5_4_oe",
"StrTokenizerTest.testBasicQuoted6_2_oe",
"StrTokenizerTest.testBasicQuoted6_3_oe",
"StrTokenizerTest.testBasicQuoted7_2_oe",
"StrTokenizerTest.testBasicQuoted7_3_oe",
"StrTokenizerTest.testBasicQuoted7_4_oe",
"StrTokenizerTest.testBasicQuotedTrimmed1_2_oe",
"StrTokenizerTest.testBasicQuotedTrimmed1_3_oe",
"StrTokenizerTest.testBasicQuotedTrimmed1_4_oe",
"StrTokenizerTest.testBasicTrimmed1_2_oe",
"StrTokenizerTest.testBasicTrimmed1_3_oe",
"StrTokenizerTest.testBasicTrimmed1_4_oe",
"StrTokenizerTest.testBasicTrimmed2_2_oe",
"StrTokenizerTest.testBasicTrimmed2_3_oe",
"StrTokenizerTest.testBasicTrimmed2_4_oe",
"StrTokenizerTest.testConstructor_String_2_oe",
"StrTokenizerTest.testConstructor_String_3_oe",
"StrTokenizerTest.testConstructor_String_char_3_oe",
"StrTokenizerTest.testConstructor_String_char_4_oe",
"StrTokenizerTest.testConstructor_String_char_char_4_oe",
"StrTokenizerTest.testConstructor_String_char_char_5_oe",
"StrTokenizerTest.testConstructor_charArray_2_oe",
"StrTokenizerTest.testConstructor_charArray_3_oe",
"StrTokenizerTest.testConstructor_charArray_char_3_oe",
"StrTokenizerTest.testConstructor_charArray_char_4_oe",
"StrTokenizerTest.testConstructor_charArray_char_char_4_oe",
"StrTokenizerTest.testConstructor_charArray_char_char_5_oe",
"ToStringBuilderTest.testObjectBuild_7_oe",
"ToStringBuilderTest.testObjectBuild_8_oe",
"BackgroundInitializerTest.testStartMultipleTimes_2_oe",
"BasicThreadFactoryTest.testNewThreadExHandler_2_oe",
"BasicThreadFactoryTest.testNewThreadPriority_2_oe",
"CallableBackgroundInitializerTest.testInitialize_2_oe",
"ConcurrentUtilsTest.testCreateIfAbsentKeyNotPresent_2_oe",
"ConcurrentUtilsTest.testCreateIfAbsentUncheckedSuccess_2_oe",
"ConcurrentUtilsTest.testPutIfAbsentKeyNotPresent_2_oe",
"EventCountCircuitBreakerTest.testClosingWhenThresholdReached_4_oe",
"EventCountCircuitBreakerTest.testNotClosingOverThreshold_2_oe",
"EventCountCircuitBreakerTest.testNotOpeningCheckIntervalExceeded_1_oe",
"ClassUtilsTest.test_getName_Object_7_oe",
"ClassUtilsTest.test_getShortCanonicalName_Class_7_oe",
"ClassUtilsTest.test_getShortCanonicalName_Class_8_oe",
"ClassUtilsTest.test_getShortCanonicalName_Object_8_oe",
"ClassUtilsTest.test_getShortCanonicalName_Object_9_oe",
"ClassUtilsTest.test_getShortClassName_Class_26_oe",
"ClassUtilsTest.test_getShortClassName_Class_27_oe",
"ClassUtilsTest.test_getShortClassName_Object_2_oe",
"ClassUtilsTest.test_getShortClassName_Object_6_oe",
"ClassUtilsTest.test_getShortClassName_Object_7_oe",
"StringEscapeUtilsTest.testEscapeXml10_4_oe",
"StringEscapeUtilsTest.testEscapeXml11_6_oe",
"StringUtilsTest.testAbbreviate_StringIntInt_17_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringIntInt_18_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringIntInt_19_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringIntInt_20_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringIntInt_21_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringIntInt_22_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringIntInt_23_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringIntInt_24_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringIntInt_25_oe_1_oe",
"StringUtilsTest.testAbbreviate_StringIntInt_25_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringIntInt_26_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringIntInt_27_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_17_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_18_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_19_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_20_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_21_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_22_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_23_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_24_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_25_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_26_oe_1_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_26_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_27_oe_3_oe",
"StringUtilsTest.testAbbreviate_StringStringIntInt_28_oe_3_oe",
"StringUtilsTest.testLang1593_1_oe",
"DiffTest.testToString_1_oe",
"CharRangeTest.testIterator_10_oe",
"CharRangeTest.testIterator_11_oe",
"CharRangeTest.testIterator_21_oe",
"CharRangeTest.testIterator_22_oe",
"CharRangeTest.testIterator_26_oe",
"CharRangeTest.testIterator_27_oe",
"CharRangeTest.testIterator_4_oe",
"CharRangeTest.testIterator_8_oe",
"CharRangeTest.testIterator_9_oe",
"CharSetTest.testConstructor_String_oddCombinations_7_oe",
"CharSetTest.testConstructor_String_oddCombinations_8_oe",
"CharSetTest.testConstructor_String_oddCombinations_9_oe",
"CharSetTest.testConstructor_String_oddNegate_10_oe",
"CharSetTest.testConstructor_String_oddNegate_11_oe",
"CharSetTest.testConstructor_String_oddNegate_12_oe",
"CharSetTest.testConstructor_String_oddNegate_14_oe",
"CharSetTest.testConstructor_String_oddNegate_16_oe",
"CharSetTest.testConstructor_String_oddNegate_17_oe",
"CharSetTest.testConstructor_String_oddNegate_18_oe",
"CharSetTest.testConstructor_String_oddNegate_19_oe",
"CharSetTest.testConstructor_String_oddNegate_20_oe",
"CharSetTest.testConstructor_String_oddNegate_21_oe",
"CharSetTest.testConstructor_String_oddNegate_4_oe",
"CharSetTest.testConstructor_String_oddNegate_5_oe",
"CharSetTest.testConstructor_String_oddNegate_6_oe",
"CharSetTest.testConstructor_String_oddNegate_8_oe",
"CharSetTest.testConstructor_String_oddNegate_9_oe",
"ClassUtilsTest.testGetInnerClass_1_oe",
"ClassUtilsTest.testGetInnerClass_2_oe",
"ClassUtilsTest.testGetInnerClass_3_oe",
"ClassUtilsTest.testGetInnerClass_4_oe",
"ClassUtilsTest.testHierarchyExcludingInterfaces_2_oe",
"ClassUtilsTest.testHierarchyExcludingInterfaces_3_oe",
"ClassUtilsTest.testHierarchyExcludingInterfaces_4_oe",
"ClassUtilsTest.testHierarchyIncludingInterfaces_2_oe",
"ClassUtilsTest.testHierarchyIncludingInterfaces_3_oe",
"ClassUtilsTest.testHierarchyIncludingInterfaces_4_oe",
"ClassUtilsTest.testHierarchyIncludingInterfaces_5_oe",
"ClassUtilsTest.test_getCanonicalName_Class_27_oe",
"ClassUtilsTest.test_getCanonicalName_Class_String_27_oe",
"ClassUtilsTest.test_getName_Class_26_oe",
"ClassUtilsTest.test_getName_Class_27_oe",
"ClassUtilsTest.test_getName_Object_2_oe",
"ClassUtilsTest.test_getName_Object_6_oe",
"DateUtilsTest.testLANG799_DE_FAIL_1_oe",

"AnnotationUtilsTest.testGeneratedAnnotationEquivalentToRealAnnotation_1_oe",
"AnnotationUtilsTest.testHashCode_1_oe",
"AnnotationUtilsTest.testToString_1_oe",

"ToStringBuilderTest.test_setUpToClass_invalid_1_oe",
"ToStringBuilderTest.testAppendToStringUsingMultiLineStyle_1_oe",
"ToStringBuilderTest.testReflectionDoubleArrayArray_1_oe",
"ToStringBuilderTest.testConstructToStringBuilder_1_oe",
"ToStringBuilderTest.testConstructToStringBuilder_2_oe",
"ToStringBuilderTest.testConstructToStringBuilder_4_oe",
"ToStringBuilderTest.testConstructToStringBuilder_5_oe",

"StringUtilsTest.testAbbreviate_StringStringIntInt_5_oe",

"CircuitBreakingExceptionTest.testThrowingEmptyException_1_oe",
"CircuitBreakingExceptionTest.testThrowingExceptionWithCause_1_oe",
"CircuitBreakingExceptionTest.testThrowingExceptionWithMessage_1_oe",
"CircuitBreakingExceptionTest.testThrowingInformativeException_1_oe",
"CloneFailedExceptionTest.testThrowingExceptionWithCause_1_oe",
"CloneFailedExceptionTest.testThrowingExceptionWithMessage_1_oe",
"CloneFailedExceptionTest.testThrowingInformativeException_1_oe",

"ArrayUtilsAddTest.*",
"FunctionsTest.*",
"ValidateTest.*",
"ComparableUtilsTest.*",
"FailableFunctionsTest.*",
"DiffResultTest.*",

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




python3 - <<'PY'
import csv
import shutil
from pathlib import Path

dataset = Path("dataset")
meta_path = dataset / "meta.csv"
inputs_path = dataset / "inputs.csv"

meta_rows = []
id2meta = {}

with meta_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    meta_fields = r.fieldnames
    for row in r:
        meta_rows.append(row)
        id2meta[row["id"]] = row

bad_ids = set()

with inputs_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    inputs_fields = r.fieldnames
    for row in r:
        if "return0" in (row.get("test_prefix") or ""):
            bad_ids.add(row["id"])

print("bad_ids", len(bad_ids))
for bid in sorted(bad_ids):
    m = id2meta.get(bid, {})
    print(bid, m.get("test_class", "?"), m.get("test_name", "?"), sep=",")

if not bad_ids:
    raise SystemExit(0)

shutil.copy2(meta_path, meta_path.with_suffix(".csv.bak"))
shutil.copy2(inputs_path, inputs_path.with_suffix(".csv.bak"))

new_meta = [row for row in meta_rows if row["id"] not in bad_ids]
with meta_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=meta_fields)
    w.writeheader()
    w.writerows(new_meta)

new_inputs = []
with inputs_path.open(newline="", encoding="utf-8") as f:
    r = csv.DictReader(f)
    for row in r:
        if row["id"] not in bad_ids:
            new_inputs.append(row)

with inputs_path.open("w", newline="", encoding="utf-8") as f:
    w = csv.DictWriter(f, fieldnames=inputs_fields)
    w.writeheader()
    w.writerows(new_inputs)

print("removed_meta", len(meta_rows) - len(new_meta))
print("removed_inputs", removed_inputs := len(bad_ids))
PY

