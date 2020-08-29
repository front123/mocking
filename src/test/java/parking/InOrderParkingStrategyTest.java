package parking;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static parking.ParkingStrategy.NO_PARKING_LOT;

public class InOrderParkingStrategyTest {

    private InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */
        //given
        Car car = mock(Car.class);
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(car.getName()).thenReturn("Make");
        when(parkingLot.getName()).thenReturn("ParkingLot1");

        //when
        Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot, car);

        //then
        assertEquals(receipt.getCarName(), "Make");
        assertEquals(receipt.getParkingLotName(), "ParkingLot1");
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        //given
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("Make");

        //when
        Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(car);

        //then
        assertEquals(receipt.getCarName(), "Make");
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        //given
        InOrderParkingStrategy inOrderParkingStrategyMock = mock(InOrderParkingStrategy.class);
        when(inOrderParkingStrategyMock.park(any(), any(Car.class))).thenCallRealMethod();
        when(inOrderParkingStrategyMock.createNoSpaceReceipt(any(Car.class))).thenCallRealMethod();
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.isFull()).thenReturn(true);
        Car car = new Car("Make");
        //when
        Receipt receipt = inOrderParkingStrategyMock.park(Collections.singletonList(parkingLot), car);

        //then
        verify(inOrderParkingStrategyMock, times(1)).createNoSpaceReceipt(any());
        verify(parkingLot, times(1)).isFull();
        assertEquals(receipt.getParkingLotName(), NO_PARKING_LOT);
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        //given
        InOrderParkingStrategy inOrderParkingStrategyMock = mock(InOrderParkingStrategy.class);
        when(inOrderParkingStrategyMock.park(any(), any(Car.class))).thenCallRealMethod();
        when(inOrderParkingStrategyMock.createReceipt(any(),any(Car.class))).thenCallRealMethod();
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.isFull()).thenReturn(false);
        when(parkingLot.getName()).thenReturn("HAParkingLot");
        Car car = new Car("Make");
        //when
        Receipt receipt = inOrderParkingStrategyMock.park(Collections.singletonList(parkingLot), car);

        //then
        verify(inOrderParkingStrategyMock, times(1)).createReceipt(any(), any());
        verify(parkingLot, times(1)).isFull();
        assertEquals(receipt.getParkingLotName(), "HAParkingLot");
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
