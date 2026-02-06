// /*
//  * Licensed to the Apache Software Foundation (ASF) under one
//  * or more contributor license agreements.  See the NOTICE file
//  * distributed with this work for additional information
//  * regarding copyright ownership.  The ASF licenses this file
//  * to you under the Apache License, Version 2.0 (the
//  * "License"); you may not use this file except in compliance
//  * with the License.  You may obtain a copy of the License at
//  *
//  * http://www.apache.org/licenses/LICENSE-2.0
//  *
//  * Unless required by applicable law or agreed to in writing,
//  * software distributed under the License is distributed on an
//  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
//  * KIND, either express or implied.  See the License for the
//  * specific language governing permissions and limitations
//  * under the License.
//  */
// package org.apache.commons.lang3;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertSame;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.lang.reflect.Constructor;
// import java.lang.reflect.Modifier;
// import java.util.Collection;
// import java.util.Collections;
// import java.util.List;
// import java.util.Map;
// import java.util.Set;

// import org.junit.jupiter.api.Nested;
// import org.junit.jupiter.api.Test;

// /**
//  * Unit tests {@link org.apache.commons.lang3.Validate}.
//  */
// class ValidateTest_OE25Dev {

//     @Nested
//     class IsTrue {

//         @Nested
//         class WithoutMessage {

//             @Test
//             void shouldNotThrowForTrueExpression() {
//                 Validate.isTrue(true);
//             }

//         }

//         @Nested
//         class WithMessage {

//             @Test
//             void shouldNotThrowForTrueExpression() {
//                 Validate.isTrue(true, "MSG");
//             }
//         }

//         @Nested
//         class WithLongTemplate {

//             @Test
//             void shouldNotThrowForTrueExpression() {
//                 Validate.isTrue(true, "MSG", 6);
//             }
//         }

//         @Nested
//         class WithDoubleTemplate {

//             @Test
//             void shouldNotThrowForTrueExpression() {
//                 Validate.isTrue(true, "MSG", 7.4d);
//             }
//         }

//         @Nested
//         class WithObjectTemplate {

//             @Test
//             void shouldNotThrowForTrueExpression() {
//                 Validate.isTrue(true, "MSG", "Object 1", "Object 2");
//             }
//         }
//     }

//     @Nested
//     class NotNull {

//         @Nested
//         class WithoutMessage {

//             @Test
//             void shouldNotThrowForNonNullReference() {
//                 Validate.notNull(new Object());
//             }
//         }

//         @Nested
//         class WithMessage {

//             @Test
//             void shouldNotThrowForNonNullReference() {
//                 Validate.notNull(new Object(), "MSG");
//             }
//         }
//     }

//     @Nested
//     class NotEmpty {

//         @Nested
//         class WithArray {

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionForArrayContainingNullReference() {
//                     Validate.notEmpty(new Object[]{null});
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionForArrayContainingNullReference() {
//                     Validate.notEmpty(new Object[]{null}, "MSG");
//                 }
//             }
//         }

//         @Nested
//         class WithCollection {

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionForCollectionContainingNullReference() {
//                     Validate.notEmpty(Collections.singleton(null));
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionForCollectionContainingNullReference() {
//                     Validate.notEmpty(Collections.singleton(null), "MSG");
//                 }
//             }
//         }

//         @Nested
//         class WithMap {

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionForMapContainingNullMapping() {
//                     Validate.notEmpty(Collections.singletonMap("key", null));
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionForMapContainingNullMapping() {
//                     Validate.notEmpty(Collections.singletonMap("key", null), "MSG");
//                 }
//             }
//         }

//         @Nested
//         class WithCharSequence {

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionForNonEmptyString() {
//                     Validate.notEmpty("Hi");
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionForNonEmptyString() {
//                     Validate.notEmpty("Hi", "MSG");
//                 }
//             }
//         }
//     }

//     @Nested
//     class NotBlank {

//         @Nested
//         class WithoutMessage {

//             @Test
//             void shouldNotThrowExceptionForNonEmptyString() {
//                 Validate.notBlank("abc");
//             }

//             @Test
//             void shouldNotThrowExceptionForNonEmptyStringContainingSpaces() {
//                 Validate.notBlank("  abc   ");
//             }

//             @Test
//             void shouldNotThrowExceptionForNonEmptyStringContainingWhitespaceChars() {
//                 Validate.notBlank(" \n \t abc \r \n ");
//             }
//         }

//         @Nested
//         class WithMessage {

//             @Test
//             void shouldNotThrowExceptionForNonEmptyString() {
//                 Validate.notBlank("abc", "MSG");
//             }

//             @Test
//             void shouldNotThrowExceptionForNonEmptyStringContainingSpaces() {
//                 Validate.notBlank("  abc   ", "MSG");
//             }

//             @Test
//             void shouldNotThrowExceptionForNonEmptyStringContainingWhitespaceChars() {
//                 Validate.notBlank(" \n \t abc \r \n ", "MSG");
//             }
//         }
//     }

//     @Nested
//     class NoNullElements {

//         @Nested
//         class WithArray {

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionForNonEmptyArray() {
//                     Validate.noNullElements(new String[]{"a", "b"});
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionForNonEmptyArray() {
//                     Validate.noNullElements(new String[]{"a", "b"}, "MSG");
//                 }
//             }
//         }

//         @Nested
//         class WithCollection {

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionForNonEmptyCollection() {
//                     Validate.noNullElements(Collections.singleton("a"));
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionForNonEmptyCollection() {
//                     Validate.noNullElements(Collections.singleton("a"), "MSG");
//                 }
//             }
//         }
//     }

//     @Nested
//     class ValidIndex {

//         @Nested
//         class WithArray {

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionForValidIndex() {
//                     Validate.validIndex(new String[]{"a"}, 0);
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionForValidIndex() {
//                     Validate.validIndex(new String[]{"a"}, 0, "MSG");
//                 }
//             }
//         }

//         @Nested
//         class WithCollection {

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionForValidIndex() {
//                     Validate.validIndex(Collections.singleton("a"), 0);
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionForValidIndex() {
//                     Validate.validIndex(Collections.singleton("a"), 0, "MSG");
//                 }
//             }
//         }

//         @Nested
//         class WithCharSequence {

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionForValidIndex() {
//                     Validate.validIndex("a", 0);
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionForValidIndex() {
//                     Validate.validIndex("a", 0, "MSG");
//                 }
//             }
//         }
//     }

//     @Nested
//     class MatchesPattern {

//         @Nested
//         class WithoutMessage {

//             @Test
//             void shouldNotThrowExceptionWhenStringMatchesPattern() {
//                 Validate.matchesPattern("hi", "[a-z]*");
//             }
//         }

//         @Nested
//         class WithMessage {

//             @Test
//             void shouldNotThrowExceptionWhenStringMatchesPattern() {
//                 Validate.matchesPattern("hi", "[a-z]*", "MSG");
//             }
//         }
//     }

//     @Nested
//     class NotNaN {

//         @Nested
//         class WithoutMessage {

//             @Test
//             void shouldNotThrowExceptionForNumber() {
//                 Validate.notNaN(0.0);
//             }

//             @Test
//             void shouldNotThrowExceptionForPositiveInfinity() {
//                 Validate.notNaN(Double.POSITIVE_INFINITY);
//             }

//             @Test
//             void shouldNotThrowExceptionForNegativeInfinity() {
//                 Validate.notNaN(Double.NEGATIVE_INFINITY);
//             }
//         }

//         @Nested
//         class WithMessage {

//             @Test
//             void shouldNotThrowExceptionForNumber() {
//                 Validate.notNaN(0.0, "MSG");
//             }

//             @Test
//             void shouldNotThrowExceptionForPositiveInfinity() {
//                 Validate.notNaN(Double.POSITIVE_INFINITY, "MSG");
//             }

//             @Test
//             void shouldNotThrowExceptionForNegativeInfinity() {
//                 Validate.notNaN(Double.NEGATIVE_INFINITY, "MSG");
//             }
//         }
//     }

//     @Nested
//     class Finite {

//         @Nested
//         class WithoutMessage {

//             @Test
//             void shouldNotThrowExceptionForFiniteValue() {
//                 Validate.finite(0.0);
//             }
//         }

//         @Nested
//         class WithMessage {

//             @Test
//             void shouldNotThrowExceptionForFiniteValue() {
//                 Validate.finite(0.0, "MSG");
//             }
//         }
//     }

//     @Nested
//     class InclusiveBetween {

//         @Nested
//         class WithComparable {

//             private static final String LOWER_BOUND = "1";
//             private static final String UPPER_BOUND = "3";

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsBetweenBounds() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, "2");
//                 }

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsLowerBound() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, LOWER_BOUND);
//                 }

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsUpperBound() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, UPPER_BOUND);
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsBetweenBounds() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, "2", "MSG");
//                 }

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsLowerBound() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, LOWER_BOUND, "MSG");
//                 }

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsUpperBound() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, UPPER_BOUND, "MSG");
//                 }
//             }
//         }

//         @Nested
//         class WithLong {

//             private static final long LOWER_BOUND = 1;
//             private static final long UPPER_BOUND = 3;

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsBetweenBounds() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, 2);
//                 }

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsLowerBound() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, LOWER_BOUND);
//                 }

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsUpperBound() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, UPPER_BOUND);
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsBetweenBounds() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, 2, "MSG");
//                 }

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsLowerBound() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, LOWER_BOUND, "MSG");
//                 }

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsUpperBound() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, UPPER_BOUND, "MSG");
//                 }
//             }
//         }

//         @Nested
//         class WithDouble {

//             private static final double LOWER_BOUND = 0.1;
//             private static final double UPPER_BOUND = 3.1;

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsBetweenBounds() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, 2.1);
//                 }

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsLowerBound() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, LOWER_BOUND);
//                 }

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsUpperBound() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, UPPER_BOUND);
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsBetweenBounds() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, 2.1, "MSG");
//                 }

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsLowerBound() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, LOWER_BOUND, "MSG");
//                 }

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsUpperBound() {
//                     Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, UPPER_BOUND, "MSG");
//                 }
//             }
//         }
//     }

//     @Nested
//     class ExclusiveBetween {

//         @Nested
//         class WithComparable {

//             private static final String LOWER_BOUND = "1";
//             private static final String UPPER_BOUND = "3";

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsBetweenBounds() {
//                     Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, "2");
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsBetweenBounds() {
//                     Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, "2", "MSG");
//                 }
//             }
//         }

//         @Nested
//         class WithLong {

//             private static final long LOWER_BOUND = 1;
//             private static final long UPPER_BOUND = 3;

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsBetweenBounds() {
//                     Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, 2);
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsBetweenBounds() {
//                     Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, 2, "MSG");
//                 }
//             }
//         }

//         @Nested
//         class WithDouble {

//             private static final double LOWER_BOUND = 0.1;
//             private static final double UPPER_BOUND = 3.1;

//             @Nested
//             class WithoutMessage {

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsBetweenBounds() {
//                     Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, 2.1);
//                 }
//             }

//             @Nested
//             class WithMessage {

//                 @Test
//                 void shouldNotThrowExceptionWhenValueIsBetweenBounds() {
//                     Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, 2.1, "MSG");
//                 }
//             }
//         }
//     }

//     @Nested
//     class IsInstanceOf {

//         @Nested
//         class WithoutMessage {

//             @Test
//             void shouldNotThrowExceptionWhenValueIsInstanceOfClass() {
//                 Validate.isInstanceOf(String.class, "hi");
//             }
//         }

//         @Nested
//         class WithMessage {

//             @Test
//             void shouldNotThrowExceptionWhenValueIsInstanceOfClass() {
//                 Validate.isInstanceOf(String.class, "hi", "MSG");
//             }
//         }

//         @Nested
//         class WithMessageTemplate {

//             @Test
//             void shouldNotThrowExceptionWhenValueIsInstanceOfClass() {
//                 Validate.isInstanceOf(String.class, "hi", "Error %s=%s", "Name", "Value");
//             }
//         }
//     }

//     @Nested
//     class IsAssignable {

//         @Nested
//         class WithoutMessage {

//             @Test
//             void shouldNotThrowExceptionWhenClassIsAssignable() {
//                 Validate.isAssignableFrom(CharSequence.class, String.class);
//             }
//         }

//         @Nested
//         class WithMessage {

//             @Test
//             void shouldNotThrowExceptionWhenClassIsAssignable() {
//                 Validate.isAssignableFrom(CharSequence.class, String.class, "MSG");
//             }
//         }
//     }

//     @Nested
//     class UtilClassConventions {
//     }

//             @Test
//             void shouldThrowExceptionWithDefaultMessageForFalseExpression_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.isTrue(false));
//             }

//             @Test
//             void shouldThrowExceptionWithDefaultMessageForFalseExpression_2_oe() {
//                 // removed other assertion

//                 assertEquals("The validated expression is false", ex.getMessage());
//             }

//             @Test
//             void shouldThrowExceptionWithGivenMessageForFalseExpression_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.isTrue(false, "MSG"));
//             }

//             @Test
//             void shouldThrowExceptionWithGivenMessageForFalseExpression_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//             @Test
//             void shouldThrowExceptionWithLongInsertedIntoTemplateMessageForFalseExpression_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.isTrue(false, "MSG %s", 6));
//             }

//             @Test
//             void shouldThrowExceptionWithLongInsertedIntoTemplateMessageForFalseExpression_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG 6", ex.getMessage());
//             }

//             @Test
//             void shouldThrowExceptionWithDoubleInsertedIntoTemplateMessageForFalseExpression_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.isTrue(false, "MSG %s", 7.4d));
//             }

//             @Test
//             void shouldThrowExceptionWithDoubleInsertedIntoTemplateMessageForFalseExpression_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG 7.4", ex.getMessage());
//             }

//             @Test
//             void shouldThrowExceptionWithDoubleInsertedIntoTemplateMessageForFalseExpression_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.isTrue(false, "MSG %s %s", "Object 1", "Object 2"));
//             }

//             @Test
//             void shouldThrowExceptionWithDoubleInsertedIntoTemplateMessageForFalseExpression_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG Object 1 Object 2", ex.getMessage());
//             }

//             @Test
//             void shouldReturnTheSameInstance_1_oe() {
//                 final String str = "Hi";
//                 final String result = Validate.notNull(str);

//                 assertSame(str, result);
//             }

//             @Test
//             void shouldThrowExceptionWithDefaultMessageForNullReference_1_oe() {
//                 final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.notNull(null));
//             }

//             @Test
//             void shouldThrowExceptionWithDefaultMessageForNullReference_2_oe() {
//                 // removed other assertion

//                 assertEquals("The validated object is null", ex.getMessage());
//             }

//             @Test
//             void shouldReturnTheSameInstance_1_oe() {
//                 final String str = "Hi";
//                 final String result = Validate.notNull(str, "MSG");

//                 assertSame(str, result);
//             }

//             @Test
//             void shouldThrowExceptionWithGivenMessageForNullReference_1_oe() {
//                 final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.notNull(null, "MSG"));
//             }

//             @Test
//             void shouldThrowExceptionWithGivenMessageForNullReference_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//                 @Test
//                 void shouldReturnTheSameInstance_1_oe() {
//                     final String[] array = new String[]{"hi"};
//                     final String[] result = Validate.notEmpty(array);

//                     assertSame(array, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullArray_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.notEmpty((Object[]) null));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullArray_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated array is empty", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForEmptyArray_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notEmpty(new Object[0]));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForEmptyArray_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated array is empty", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnTheSameInstance_1_oe() {
//                     final String[] array = new String[]{"hi"};
//                     final String[] result = Validate.notEmpty(array, "MSG");

//                     assertSame(array, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithGivenMessageForNullArray_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.notEmpty((Object[]) null, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithGivenMessageForNullArray_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForEmptyArray_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notEmpty(new Object[0], "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForEmptyArray_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnTheSameInstance_1_oe() {
//                     final Set<String> col = Collections.singleton("Hi");
//                     final Set<String> result = Validate.notEmpty(col);

//                     assertSame(col, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullCollection_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.notEmpty((Collection<?>) null));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullCollection_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated collection is empty", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForEmptyCollection_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notEmpty(Collections.emptySet()));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForEmptyCollection_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated collection is empty", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnTheSameInstance_1_oe() {
//                     final Set<String> col = Collections.singleton("Hi");
//                     final Set<String> result = Validate.notEmpty(col, "MSG");

//                     assertSame(col, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithGivenMessageForNullCollection_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.notEmpty((Collection<?>) null, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithGivenMessageForNullCollection_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageForEmptyCollection_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notEmpty(Collections.emptySet(), "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageForEmptyCollection_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnTheSameInstance_1_oe() {
//                     final Map<String, String> map = Collections.singletonMap("key", "value");
//                     final Map<String, String> result = Validate.notEmpty(map);

//                     assertSame(map, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullMap_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.notEmpty((Map<?, ?>) null));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullMap_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated map is empty", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForEmptyMap_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notEmpty(Collections.emptyMap()));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForEmptyMap_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated map is empty", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnTheSameInstance_1_oe() {
//                     final Map<String, String> map = Collections.singletonMap("key", "value");
//                     final Map<String, String> result = Validate.notEmpty(map, "MSG");

//                     assertSame(map, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithGivenMessageForNullMap_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.notEmpty((Map<?, ?>) null, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithGivenMessageForNullMap_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageForEmptyMap_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notEmpty(Collections.emptyMap(), "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageForEmptyMap_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnTheSameInstance_1_oe() {
//                     final String str = "Hi";
//                     final String result = Validate.notEmpty(str);

//                     assertSame(str, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullCharSequence_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.notEmpty((CharSequence) null));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullCharSequence_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated character sequence is empty", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForEmptyString_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notEmpty(""));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForEmptyString_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated character sequence is empty", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnTheSameInstance_1_oe() {
//                     final String str = "Hi";
//                     final String result = Validate.notEmpty(str, "MSG");

//                     assertSame(str, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithGivenMessageForNullCharSequence_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.notEmpty((CharSequence) null, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithGivenMessageForNullCharSequence_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageForEmptyString_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notEmpty("", "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageForEmptyString_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//             @Test
//             void shouldReturnNonBlankValue_1_oe() {
//                 final String str = "abc";
//                 final String result = Validate.notBlank(str);

//                 assertSame(str, result);
//             }

//             @Test
//             void shouldThrowNullPointerExceptionWithDefaultMessageForNullString_1_oe() {
//                 final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.notBlank(null));
//             }

//             @Test
//             void shouldThrowNullPointerExceptionWithDefaultMessageForNullString_2_oe() {
//                 // removed other assertion

//                 assertEquals("The validated character sequence is blank", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForEmptyString_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notBlank(""));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForEmptyString_2_oe() {
//                 // removed other assertion

//                 assertEquals("The validated character sequence is blank", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForBlankString_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notBlank("   "));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForBlankString_2_oe() {
//                 // removed other assertion

//                 assertEquals("The validated character sequence is blank", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForStringContainingOnlyWhitespaceChars_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notBlank(" \n \t \r \n "));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForStringContainingOnlyWhitespaceChars_2_oe() {
//                 // removed other assertion

//                 assertEquals("The validated character sequence is blank", ex.getMessage());
//             }

//             @Test
//             void shouldReturnNonBlankValue_1_oe() {
//                 final String str = "abc";
//                 final String result = Validate.notBlank(str, "MSG");

//                 assertSame(str, result);
//             }

//             @Test
//             void shouldThrowNullPointerExceptionWithGivenMessageForNullString_1_oe() {
//                 final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.notBlank(null, "MSG"));
//             }

//             @Test
//             void shouldThrowNullPointerExceptionWithGivenMessageForNullString_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGivenMessageForEmptyString_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notBlank("", "MSG"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGivenMessageForEmptyString_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGivenMessageForBlankString_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notBlank("   ", "MSG"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGivenMessageForBlankString_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGivenMessageForStringContainingOnlyWhitespaceChars_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notBlank(" \n \t \r \n ", "MSG"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGivenMessageForStringContainingOnlyWhitespaceChars_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//                 @Test
//                 void shouldReturnSameInstance_1_oe() {
//                     final String[] array = {"a", "b"};
//                     final String[] result = Validate.noNullElements(array);

//                     assertSame(array, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullArray_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.noNullElements((Object[]) null));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullArray_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated object is null", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForArrayWithNullElement_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.noNullElements(new String[]{"a", null}));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForArrayWithNullElement_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated array contains null element at index: 1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnSameInstance_1_oe() {
//                     final String[] array = {"a", "b"};
//                     final String[] result = Validate.noNullElements(array, "MSG");

//                     assertSame(array, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullArray_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.noNullElements((Object[]) null, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullArray_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated object is null", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageForArrayWithNullElement_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.noNullElements(new String[]{"a", null}, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageForArrayWithNullElement_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnSameInstance_1_oe() {
//                     final Set<String> col = Collections.singleton("a");
//                     final Set<String> result = Validate.noNullElements(col);

//                     assertSame(col, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullCollection_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.noNullElements((Collection<?>) null));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullCollection_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated object is null", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForCollectionWithNullElement_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.noNullElements(Collections.singleton(null)));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageForCollectionWithNullElement_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated collection contains null element at index: 0", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnSameInstance_1_oe() {
//                     final Set<String> col = Collections.singleton("a");
//                     final Set<String> result = Validate.noNullElements(col, "MSG");

//                     assertSame(col, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullCollection_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.noNullElements((Collection<?>) null, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullCollection_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated object is null", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageForCollectionWithNullElement_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.noNullElements(Collections.singleton(null), "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageForCollectionWithNullElement_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnSameInstance_1_oe() {
//                     final String[] array = {"a"};
//                     final String[] result = Validate.validIndex(array, 0);

//                     assertSame(array, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultForNullArray_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.validIndex((Object[]) null, 1));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultForNullArray_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated object is null", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithDefaultMessageForNegativeIndex_1_oe() {
//                     final IndexOutOfBoundsException ex = assertThrows( IndexOutOfBoundsException.class, () -> Validate.validIndex(new String[]{"a"}, -1));
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithDefaultMessageForNegativeIndex_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated array index is invalid: -1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithDefaultMessageForIndexOutOfBounds_1_oe() {
//                     final IndexOutOfBoundsException ex = assertThrows( IndexOutOfBoundsException.class, () -> Validate.validIndex(new String[]{"a"}, 1));
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithDefaultMessageForIndexOutOfBounds_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated array index is invalid: 1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnSameInstance_1_oe() {
//                     final String[] array = {"a"};
//                     final String[] result = Validate.validIndex(array, 0, "MSG");

//                     assertSame(array, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullArray_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.validIndex((Object[]) null, 1, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullArray_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated object is null", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithGivenMessageForNegativeIndex_1_oe() {
//                     final IndexOutOfBoundsException ex = assertThrows( IndexOutOfBoundsException.class, () -> Validate.validIndex(new String[]{"a"}, -1, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithGivenMessageForNegativeIndex_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithGivenMessageForIndexOutOfBounds_1_oe() {
//                     final IndexOutOfBoundsException ex = assertThrows( IndexOutOfBoundsException.class, () -> Validate.validIndex(new String[]{"a"}, 1, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithGivenMessageForIndexOutOfBounds_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnSameInstance_1_oe() {
//                     final Set<String> col = Collections.singleton("a");
//                     final Set<String> result = Validate.validIndex(col, 0);

//                     assertSame(col, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultForNullCollection_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.validIndex((Collection<?>) null, 1));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultForNullCollection_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated object is null", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithDefaultMessageForNegativeIndex_1_oe() {
//                     final IndexOutOfBoundsException ex = assertThrows( IndexOutOfBoundsException.class, () -> Validate.validIndex(Collections.singleton("a"), -1));
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithDefaultMessageForNegativeIndex_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated collection index is invalid: -1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithDefaultMessageForIndexOutOfBounds_1_oe() {
//                     final IndexOutOfBoundsException ex = assertThrows( IndexOutOfBoundsException.class, () -> Validate.validIndex(Collections.singleton("a"), 1));
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithDefaultMessageForIndexOutOfBounds_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated collection index is invalid: 1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnSameInstance_1_oe() {
//                     final Set<String> col = Collections.singleton("a");
//                     final Set<String> result = Validate.validIndex(col, 0, "MSG");

//                     assertSame(col, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullCollection_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.validIndex((Collection<?>) null, 1, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullCollection_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated object is null", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithGivenMessageForNegativeIndex_1_oe() {
//                     final IndexOutOfBoundsException ex = assertThrows( IndexOutOfBoundsException.class, () -> Validate.validIndex(Collections.singleton("a"), -1, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithGivenMessageForNegativeIndex_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithGivenMessageForIndexOutOfBounds_1_oe() {
//                     final IndexOutOfBoundsException ex = assertThrows( IndexOutOfBoundsException.class, () -> Validate.validIndex(Collections.singleton("a"), 1, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithGivenMessageForIndexOutOfBounds_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnSameInstance_1_oe() {
//                     final String str = "a";
//                     final String result = Validate.validIndex(str, 0);

//                     assertSame(str, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultForNullString_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.validIndex((String) null, 1));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultForNullString_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated object is null", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithDefaultMessageForNegativeIndex_1_oe() {
//                     final IndexOutOfBoundsException ex = assertThrows( IndexOutOfBoundsException.class, () -> Validate.validIndex("a", -1));
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithDefaultMessageForNegativeIndex_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated character sequence index is invalid: -1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithDefaultMessageForIndexOutOfBounds_1_oe() {
//                     final IndexOutOfBoundsException ex = assertThrows( IndexOutOfBoundsException.class, () -> Validate.validIndex("a", 1));
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithDefaultMessageForIndexOutOfBounds_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated character sequence index is invalid: 1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldReturnSameInstance_1_oe() {
//                     final String str = "a";
//                     final String result = Validate.validIndex(str, 0, "MSG");

//                     assertSame(str, result);
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullStr_1_oe() {
//                     final NullPointerException ex = assertThrows( NullPointerException.class, () -> Validate.validIndex((String) null, 1, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowNullPointerExceptionWithDefaultMessageForNullStr_2_oe() {
//                     // removed other assertion

//                     assertEquals("The validated object is null", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithGivenMessageForNegativeIndex_1_oe() {
//                     final IndexOutOfBoundsException ex = assertThrows( IndexOutOfBoundsException.class, () -> Validate.validIndex("a", -1, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithGivenMessageForNegativeIndex_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithGivenMessageForIndexOutOfBounds_1_oe() {
//                     final IndexOutOfBoundsException ex = assertThrows( IndexOutOfBoundsException.class, () -> Validate.validIndex("a", 1, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIndexOutOfBoundsExceptionWithGivenMessageForIndexOutOfBounds_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenStringDoesNotMatchPattern_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.matchesPattern("hi", "[0-9]*"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenStringDoesNotMatchPattern_2_oe() {
//                 // removed other assertion

//                 assertEquals("The string hi does not match the pattern [0-9]*", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWhenStringDoesNotMatchPattern_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.matchesPattern("hi", "[0-9]*", "MSG"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWhenStringDoesNotMatchPattern_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForNaN_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notNaN(Double.NaN));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForNaN_2_oe() {
//                 // removed other assertion

//                 assertEquals("The validated value is not a number", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGivenMessageForNaN_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.notNaN(Double.NaN, "MSG"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGivenMessageForNaN_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForPositiveInfinity_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.finite(Double.POSITIVE_INFINITY));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForPositiveInfinity_2_oe() {
//                 // removed other assertion

//                 assertEquals("The value is invalid: Infinity", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForNegativeInfinity_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.finite(Double.NEGATIVE_INFINITY));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForNegativeInfinity_2_oe() {
//                 // removed other assertion

//                 assertEquals("The value is invalid: -Infinity", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForNaN_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.finite(Double.NaN));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForNaN_2_oe() {
//                 // removed other assertion

//                 assertEquals("The value is invalid: NaN", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForPositiveInfinity_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.finite(Double.POSITIVE_INFINITY, "MSG"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForPositiveInfinity_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForNegativeInfinity_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.finite(Double.NEGATIVE_INFINITY, "MSG"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForNegativeInfinity_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForNaN_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.finite(Double.NaN, "MSG"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageForNaN_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsBelowLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, "0"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsBelowLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 0 is not in the specified inclusive range of 1 to 3", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, "4"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 4 is not in the specified inclusive range of 1 to 3", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsBelowLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, "0", "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsBelowLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsAboveUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, "4", "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsAboveUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsBelowLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, 0));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsBelowLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 0 is not in the specified inclusive range of 1 to 3", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, 4));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 4 is not in the specified inclusive range of 1 to 3", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsBelowLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, 0, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsBelowLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsAboveUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, 4, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsAboveUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsBelowLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, 0.01));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsBelowLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 0.01 is not in the specified inclusive range of 0.1 to 3.1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, 4.1));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 4.1 is not in the specified inclusive range of 0.1 to 3.1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsBelowLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, 0.01, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsBelowLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsAboveUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.inclusiveBetween(LOWER_BOUND, UPPER_BOUND, 4.1, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsAboveUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, LOWER_BOUND));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 1 is not in the specified exclusive range of 1 to 3", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, UPPER_BOUND));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 3 is not in the specified exclusive range of 1 to 3", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsBelowLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, "0"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsBelowLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 0 is not in the specified exclusive range of 1 to 3", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, "4"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 4 is not in the specified exclusive range of 1 to 3", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, LOWER_BOUND, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, UPPER_BOUND, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsBelowLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, "0", "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsBelowLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, "4", "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, LOWER_BOUND));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 1 is not in the specified exclusive range of 1 to 3", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, UPPER_BOUND));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 3 is not in the specified exclusive range of 1 to 3", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsBelowLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, 0));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsBelowLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 0 is not in the specified exclusive range of 1 to 3", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, 4));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 4 is not in the specified exclusive range of 1 to 3", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, LOWER_BOUND, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, UPPER_BOUND, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsBelowLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, 0, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsBelowLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, 4, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExcdeptionWhenValueIsLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, LOWER_BOUND));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExcdeptionWhenValueIsLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 0.1 is not in the specified exclusive range of 0.1 to 3.1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExcdeptionWhenValueIsUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, UPPER_BOUND));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExcdeptionWhenValueIsUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 3.1 is not in the specified exclusive range of 0.1 to 3.1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsBelowLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, 0.01));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsBelowLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 0.01 is not in the specified exclusive range of 0.1 to 3.1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, 4.1));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsAboveUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("The value 4.1 is not in the specified exclusive range of 0.1 to 3.1", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExcdeptionWhenValueIsLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, LOWER_BOUND, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExcdeptionWhenValueIsLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExcdeptionWhenValueIsUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, UPPER_BOUND, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExcdeptionWhenValueIsUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsBelowLowerBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, 0.01, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsBelowLowerBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsAboveUpperBound_1_oe() {
//                     final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.exclusiveBetween(LOWER_BOUND, UPPER_BOUND, 4.1, "MSG"));
//                 }

//                 @Test
//                 void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsAboveUpperBound_2_oe() {
//                     // removed other assertion

//                     assertEquals("MSG", ex.getMessage());
//                 }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsNotInstanceOfClass_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.isInstanceOf(List.class, "hi"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenValueIsNotInstanceOfClass_2_oe() {
//                 // removed other assertion

//                 assertEquals("Expected type: java.util.List, actual: java.lang.String", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsNotInstanceOfClass_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.isInstanceOf(List.class, "hi", "MSG"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsNotInstanceOfClass_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsNotInstanceOfClass_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.isInstanceOf(List.class, "hi", "Error %s=%s", "Name", "Value"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGivenMessageWhenValueIsNotInstanceOfClass_2_oe() {
//                 // removed other assertion

//                 assertEquals("Error Name=Value", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenClassIsNotAssignable_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.isAssignableFrom(List.class, String.class));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithDefaultMessageWhenClassIsNotAssignable_2_oe() {
//                 // removed other assertion

//                 assertEquals("Cannot assign a java.lang.String to a java.util.List", ex.getMessage());
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGiventMessageWhenClassIsNotAssignable_1_oe() {
//                 final IllegalArgumentException ex = assertThrows( IllegalArgumentException.class, () -> Validate.isAssignableFrom(List.class, String.class, "MSG"));
//             }

//             @Test
//             void shouldThrowIllegalArgumentExceptionWithGiventMessageWhenClassIsNotAssignable_2_oe() {
//                 // removed other assertion

//                 assertEquals("MSG", ex.getMessage());
//             }

//         @Test
//         void instancesCanBeConstrcuted_1_oe() {
//             assertNotNull(new Validate());
//         }

//         @Test
//         void hasOnlyOnePublicConstructor_1_oe() {
//             final Constructor<?>[] cons = Validate.class.getDeclaredConstructors();
//             assertEquals(1, cons.length);
//         }

//         @Test
//         void isPublicClass_1_oe() {
//             assertTrue(Modifier.isPublic(Validate.class.getModifiers()));
//         }

//         @Test
//         void isNonFinalClass_1_oe() {
//             assertFalse(Modifier.isFinal(Validate.class.getModifiers()));
//         }

// }
