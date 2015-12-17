package com.cer.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cer.dao.DCMDao;

import intel.rssdk.PXCMCapture;
import intel.rssdk.PXCMFaceConfiguration;
import intel.rssdk.PXCMFaceData;
import intel.rssdk.PXCMFaceModule;
import intel.rssdk.PXCMRectI32;
import intel.rssdk.PXCMSenseManager;
import intel.rssdk.pxcmStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:CER.xml" })
/*
 * @TransactionConfiguration(transactionManager = "telenorTransactionManager",
 * defaultRollback = true)
 * 
 * @Transactional
 */
public class FaceTrackingJunit {
	@Autowired
	private DCMDao dcmDao;

	@Test
	public void testFaceTrackingJunit() {
		PXCMSenseManager senseMgr = PXCMSenseManager.CreateInstance();
		if (senseMgr == null) {
			System.out.println("Failed to create Sense Manager");
			return;

		}
		pxcmStatus sts = senseMgr.EnableFace(null);
		PXCMFaceModule faceModule = senseMgr.QueryFace();
		if (sts.isError() || faceModule == null) {
			/*boolean result = dcmDao.restartTheDCMService();
			if (!result) {*/
				System.out.println("Failed to initialise Face Module");
				senseMgr.Close();
				return;
			/*} else {
				faceModule = senseMgr.QueryFace();
			}*/
		}
		sts = pxcmStatus.PXCM_STATUS_DATA_UNAVAILABLE;
		PXCMFaceConfiguration faceConfig = faceModule.CreateActiveConfiguration();
		faceConfig.SetTrackingMode(PXCMFaceConfiguration.TrackingModeType.FACE_MODE_COLOR_PLUS_DEPTH);
		faceConfig.detection.isEnabled = true;
		faceConfig.ApplyChanges();
		faceConfig.Update();
		//senseMgr.QueryCaptureManager().SetRealtime(true);
		sts = senseMgr.Init();

		if (sts.isError()) {
			System.out.println("Init failed: " + sts);
			senseMgr.Close();
			return;
		}

		PXCMCapture.Device dev = senseMgr.QueryCaptureManager().QueryDevice();
		PXCMCapture.DeviceInfo info = new PXCMCapture.DeviceInfo();
		dev.QueryDeviceInfo(info);
		System.out.println("Using Camera: " + info.name);

		PXCMFaceData faceData = faceModule.CreateOutput();

		for (int nframes = 0; nframes < 30000; nframes++) {
			senseMgr.AcquireFrame(true);

			PXCMCapture.Sample sample = senseMgr.QueryFaceSample();

			// faceData = faceModule.CreateOutput();
			faceData.Update();

			// Read and print data
			for (int fidx = 0;; fidx++) {
				PXCMFaceData.Face face = faceData.QueryFaceByIndex(fidx);
				if (face == null)
					break;
				PXCMFaceData.DetectionData detectData = face.QueryDetection();

				if (detectData != null) {
					PXCMRectI32 rect = new PXCMRectI32();
					boolean ret = detectData.QueryBoundingRect(rect);
					if (ret) {
						System.out.println("");
						System.out.println("Detection Rectangle at frame #" + nframes);
						System.out.println("Top Left corner: (" + rect.x + "," + rect.y + ")");
						System.out.println("Height: " + rect.h + " Width: " + rect.w);
					}
				} else
					break;

				PXCMFaceData.PoseData poseData = face.QueryPose();
				if (poseData != null) {
					PXCMFaceData.PoseEulerAngles pea = new PXCMFaceData.PoseEulerAngles();
					poseData.QueryPoseAngles(pea);
					System.out.println("Pose Data at frame #" + nframes);
					System.out.println("(Roll, Yaw, Pitch) = (" + pea.roll + "," + pea.yaw + "," + pea.pitch + ")");
				}
			}

			// faceData.close();
			senseMgr.ReleaseFrame();
		}
		faceData.close();
		senseMgr.Close();
		System.exit(0);
	}
}
