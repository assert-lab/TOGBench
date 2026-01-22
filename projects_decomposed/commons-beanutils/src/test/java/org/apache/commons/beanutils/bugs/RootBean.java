package org.apache.commons.beanutils.bugs;

import java.util.List;

@SuppressWarnings("rawtypes")
class RootBean {

    private List file;

    public List getFile() {
        return file;
    }

    public void setFile(final List file) {
        this.file = file;
    }

    public String getFile(final int i) {
        return (String) file.get(i);
    }

    @SuppressWarnings("unchecked")
    public void setFile(final int i, final String file) {
        this.file.set(i, file);
    }
}