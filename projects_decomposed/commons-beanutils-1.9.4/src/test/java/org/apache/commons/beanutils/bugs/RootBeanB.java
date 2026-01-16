package org.apache.commons.beanutils.bugs;

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

class FirstChildBeanB extends RootBeanB {
}

class SecondChildBeanB extends RootBeanB {
}
