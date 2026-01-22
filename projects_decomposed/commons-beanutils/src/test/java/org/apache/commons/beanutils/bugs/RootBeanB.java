package org.apache.commons.beanutils.bugs;

import static org.junit.Assert.assertEquals;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;


class RootBeanB {

    private String[] file;

    public String[] getFile() {
        return file;
    }

    public void setFile(final String[] file) {
        this.file = file;
    }

    public String getFile(final int i) {
        return file[i];
    }

    public void setFile(final int i, final String file) {
        this.file[i] = file;
    }

}
