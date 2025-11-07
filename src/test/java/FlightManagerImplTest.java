import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import models.*;
import exceptions.*;
import dto.*;
import java.util.Map;
import java.util.List;

public class FlightManagerImplTest {
    private FlightManager fm;
    @Before
    public void setUp() {
        fm = FlightManagerImpl.getInstance();
        fm.addAvio(123, "B-2", "Vueling");
    }

    @After
    public void tearDown() {
        fm = null;
    }


}