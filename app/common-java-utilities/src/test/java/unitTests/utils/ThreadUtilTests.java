package unitTests.utils;

import io.diablo73.common.utils.DateUtil;
import io.diablo73.common.utils.ThreadUtil;
import org.apache.commons.lang3.ThreadUtils;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Random;

/**
 * @author Diablo73
 * @version 1.0 <br> 10/03/2022
 * @since 02/03/2022
 */

public class ThreadUtilTests {

	private static final int duration = new Random().nextInt(5000);


	@Test
	public void waitTestCase() {
		Date startTime = new Date();
		ThreadUtil.wait(duration);
		Date endTime = new Date();
		Assert.assertTrue(DateUtil.getDiffMilliseconds(endTime, startTime) >= 0);
	}

	@Test
	public void waitExceptionTestCase() {
		ChildThread childThread = new ChildThread();
		childThread.start();
		childThread.interrupt();
	}

	@Test
	public void sleepTestCase() {
		Date startTime = new Date();
		ThreadUtil.sleep(duration);
		Date endTime = new Date();
		Assert.assertTrue(DateUtil.getDiffMilliseconds(endTime, startTime) >= 0);
	}

	@Test
	public void sleepExceptionTestCase() {
		MockedStatic<ThreadUtils> threadUtilsMockedStatic = Mockito.mockStatic(ThreadUtils.class);
		InterruptedException interruptedException = new InterruptedException("Exception output of ThreadUtilTests.waitExceptionTestCase()!!!");
		threadUtilsMockedStatic.when(() -> ThreadUtils.sleep(Mockito.any())).thenThrow(interruptedException);
		ThreadUtil.sleep(duration);
		threadUtilsMockedStatic.close();
	}


	public static class ChildThread extends Thread {
		public void run() {
			ThreadUtil.wait(duration);
		}
	}
}
