/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 */
public class MultilineRecursiveToStringStyleTest_OE25Dev {

    private final String BR = System.lineSeparator();

    private String getClassPrefix(final Object object) {
        return object.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(object));
    }

    private String toString(final Object object) {
        return new ReflectionToStringBuilder(object, new MultilineRecursiveToStringStyle()).toString();
    }

    static class WithArrays {
        boolean[] boolArray;
        char[] charArray;
        double[] doubleArray;
        int[] intArray;
        long[] longArray;
        String[] stringArray;
    }

    static class Bank {
        String name;

        Bank(final String name) {
            this.name = name;
        }
    }

    static class Customer {
        String name;
        Bank bank;
        List<Account> accounts;

        Customer(final String name) {
            this.name = name;
        }
    }

    static class Account {
        Customer owner;
        List<Transaction> transactions = new ArrayList<>();

        public double getBalance() {
            double balance = 0;
            for (final Transaction tx : transactions) {
                balance += tx.amount;
            }
            return balance;
        }
    }

    static class Transaction {
        double amount;
        String date;

        Transaction(final String datum, final double betrag) {
            this.date = datum;
            this.amount = betrag;
        }
    }


}
