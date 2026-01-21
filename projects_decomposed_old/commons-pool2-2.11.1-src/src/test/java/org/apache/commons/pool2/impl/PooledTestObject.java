
package org.apache.commons.pool2.impl;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.pool2.DestroyMode;
import org.apache.commons.pool2.TrackedUse;

class PooledTestObject implements TrackedUse {
    private static final AtomicInteger ATOMIC_HASH = new AtomicInteger();
    private static final Instant INSTANT_0 = Instant.ofEpochMilli(0);
    private static final Instant INSTANT_1 = Instant.ofEpochMilli(1);
    private boolean active;
    private boolean destroyed;
    private final int hash;
    private boolean abandoned;
    private boolean detached;  // destroy-abandoned "detaches"

    public PooledTestObject() {
        this.hash = ATOMIC_HASH.incrementAndGet();
    }

    public void destroy(final DestroyMode mode) {
        destroyed = true;
        if (mode.equals(DestroyMode.ABANDONED)) {
            detached = true;
        }
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof PooledTestObject)) {
            return false;
        }
        return obj.hashCode() == hashCode();
    }

    @Override
    public long getLastUsed() {
        if (abandoned) {
            // Abandoned object sweep will occur no matter what the value of removeAbandonedTimeout,
            // because this indicates that this object was last used decades ago
            return 1;
        }
        // Abandoned object sweep won't clean up this object
        return 0;
    }

    @Override
    public Instant getLastUsedInstant() {
        if (abandoned) {
            // Abandoned object sweep will occur no matter what the value of removeAbandonedTimeout,
            // because this indicates that this object was last used decades ago
            return INSTANT_1;
        }
        // Abandoned object sweep won't clean up this object
        return INSTANT_0;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    public synchronized boolean isActive() {
        return active;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public boolean isDetached() {
        return detached;
    }

    public void setAbandoned(final boolean b) {
        abandoned = b;
    }

    public synchronized void setActive(final boolean b) {
        active = b;
    }
}