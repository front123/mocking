package parking;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static parking.ParkingStrategy.NO_PARKING_LOT;

public class VipParkingStrategyTest {

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
        //given
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        Car car = new Car("VipCarA");
        ParkingLot parkingLot = spy(new ParkingLot("ParkingLotA", 2));
        doReturn(true).when(parkingLot).isFull();
        doReturn(true).when(vipParkingStrategy).isAllowOverPark(any());
        //when
        Receipt receipt = vipParkingStrategy.park(Collections.singletonList(parkingLot), car);
        //then
        verify(vipParkingStrategy, times(1)).createReceipt(any(),any());
        verify(parkingLot, times(1)).isFull();
        assertEquals("ParkingLotA", receipt.getParkingLotName());
        assertEquals("VipCarA", receipt.getCarName());
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        //given
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        Car car = new Car("CarB");
        ParkingLot parkingLot = spy(new ParkingLot("ParkingLotA", 2));
        doReturn(true).when(parkingLot).isFull();
        //when
        Receipt receipt = vipParkingStrategy.park(Collections.singletonList(parkingLot), car);
        //then
        verify(vipParkingStrategy, times(1)).createNoSpaceReceipt(any());
        verify(parkingLot, times(1)).isFull();
        assertEquals(NO_PARKING_LOT, receipt.getParkingLotName());
        assertEquals("CarB", receipt.getCarName());
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
