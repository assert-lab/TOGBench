package spark;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FilterImplTest_OE25Dev {

    public String PATH_TEST;
    public String ACCEPT_TYPE_TEST;

    public FilterImpl filter;

    @Before
    public void setup(){
        PATH_TEST = "/etc/test";
        ACCEPT_TYPE_TEST  = "test/*";
    }

    @Test
    public void testGets_thenReturnGetPathAndGetAcceptTypeSuccessfully() throws Exception {
        filter = FilterImpl.create(PATH_TEST, ACCEPT_TYPE_TEST, null);
        assertEquals("Should return path specified", PATH_TEST, filter.getPath());
        assertEquals("Should return accept type specified", ACCEPT_TYPE_TEST, filter.getAcceptType());
    }

    @Test
    public void testCreate_whenOutAssignAcceptTypeInTheParameters_thenReturnPathAndAcceptTypeSuccessfully(){
        filter = FilterImpl.create(PATH_TEST, null);
        assertEquals("Should return path specified", PATH_TEST, filter.getPath());
        assertEquals("Should return accept type specified", RouteImpl.DEFAULT_ACCEPT_TYPE, filter.getAcceptType());
    }

    @Test
    public void testCreate_whenAcceptTypeNullValueInTheParameters_thenReturnPathAndAcceptTypeSuccessfully(){
        filter = FilterImpl.create(PATH_TEST, null, null);
        assertEquals("Should return path specified", PATH_TEST, filter.getPath());
        assertEquals("Should return accept type specified", RouteImpl.DEFAULT_ACCEPT_TYPE, filter.getAcceptType());
    }

    @Test
    public void testConstructor_1_oe(){
        FilterImpl filter = new FilterImpl(PATH_TEST, ACCEPT_TYPE_TEST) {
            @Override
            public void handle(Request request, Response response) throws Exception {
            }
        };
        assertEquals("Should return path specified", PATH_TEST, filter.getPath());
    }

    @Test
    public void testConstructor_2_oe(){
        FilterImpl filter = new FilterImpl(PATH_TEST, ACCEPT_TYPE_TEST) {
            @Override
            public void handle(Request request, Response response) throws Exception {
            }
        };
        // removed other assertion
        assertEquals("Should return accept type specified", ACCEPT_TYPE_TEST, filter.getAcceptType());
    }

    @Test
    public void testConstructor_3_oe(){
        FilterImpl filter = new FilterImpl(PATH_TEST, ACCEPT_TYPE_TEST) {
            @Override
            public void handle(Request request, Response response) throws Exception {
            }
        };
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGets_thenReturnGetPathAndGetAcceptTypeSuccessfully() throws Exception {
        filter = FilterImpl.create(PATH_TEST, ACCEPT_TYPE_TEST, null);
        assertEquals("Should return path specified", PATH_TEST, filter.getPath());
    }

    @Test
    public void testConstructor_4_oe(){
        FilterImpl filter = new FilterImpl(PATH_TEST, ACCEPT_TYPE_TEST) {
            @Override
            public void handle(Request request, Response response) throws Exception {
            }
        };
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGets_thenReturnGetPathAndGetAcceptTypeSuccessfully() throws Exception {
        filter = FilterImpl.create(PATH_TEST, ACCEPT_TYPE_TEST, null);
        // removed other assertion
        assertEquals("Should return accept type specified", ACCEPT_TYPE_TEST, filter.getAcceptType());
    }

    @Test
    public void testConstructor_5_oe(){
        FilterImpl filter = new FilterImpl(PATH_TEST, ACCEPT_TYPE_TEST) {
            @Override
            public void handle(Request request, Response response) throws Exception {
            }
        };
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGets_thenReturnGetPathAndGetAcceptTypeSuccessfully() throws Exception {
        filter = FilterImpl.create(PATH_TEST, ACCEPT_TYPE_TEST, null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testCreate_whenOutAssignAcceptTypeInTheParameters_thenReturnPathAndAcceptTypeSuccessfully(){
        filter = FilterImpl.create(PATH_TEST, null);
        assertEquals("Should return path specified", PATH_TEST, filter.getPath());
    }

    @Test
    public void testConstructor_6_oe(){
        FilterImpl filter = new FilterImpl(PATH_TEST, ACCEPT_TYPE_TEST) {
            @Override
            public void handle(Request request, Response response) throws Exception {
            }
        };
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGets_thenReturnGetPathAndGetAcceptTypeSuccessfully() throws Exception {
        filter = FilterImpl.create(PATH_TEST, ACCEPT_TYPE_TEST, null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testCreate_whenOutAssignAcceptTypeInTheParameters_thenReturnPathAndAcceptTypeSuccessfully(){
        filter = FilterImpl.create(PATH_TEST, null);
        // removed other assertion
        assertEquals("Should return accept type specified", RouteImpl.DEFAULT_ACCEPT_TYPE, filter.getAcceptType());
    }

    @Test
    public void testConstructor_7_oe(){
        FilterImpl filter = new FilterImpl(PATH_TEST, ACCEPT_TYPE_TEST) {
            @Override
            public void handle(Request request, Response response) throws Exception {
            }
        };
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGets_thenReturnGetPathAndGetAcceptTypeSuccessfully() throws Exception {
        filter = FilterImpl.create(PATH_TEST, ACCEPT_TYPE_TEST, null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testCreate_whenOutAssignAcceptTypeInTheParameters_thenReturnPathAndAcceptTypeSuccessfully(){
        filter = FilterImpl.create(PATH_TEST, null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testCreate_whenAcceptTypeNullValueInTheParameters_thenReturnPathAndAcceptTypeSuccessfully(){
        filter = FilterImpl.create(PATH_TEST, null, null);
        assertEquals("Should return path specified", PATH_TEST, filter.getPath());
    }

    @Test
    public void testConstructor_8_oe(){
        FilterImpl filter = new FilterImpl(PATH_TEST, ACCEPT_TYPE_TEST) {
            @Override
            public void handle(Request request, Response response) throws Exception {
            }
        };
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testGets_thenReturnGetPathAndGetAcceptTypeSuccessfully() throws Exception {
        filter = FilterImpl.create(PATH_TEST, ACCEPT_TYPE_TEST, null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testCreate_whenOutAssignAcceptTypeInTheParameters_thenReturnPathAndAcceptTypeSuccessfully(){
        filter = FilterImpl.create(PATH_TEST, null);
        // removed other assertion
        // removed other assertion
    }

    @Test
    public void testCreate_whenAcceptTypeNullValueInTheParameters_thenReturnPathAndAcceptTypeSuccessfully(){
        filter = FilterImpl.create(PATH_TEST, null, null);
        // removed other assertion
        assertEquals("Should return accept type specified", RouteImpl.DEFAULT_ACCEPT_TYPE, filter.getAcceptType());
    }

}