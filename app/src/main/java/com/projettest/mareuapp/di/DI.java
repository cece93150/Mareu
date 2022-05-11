package com.projettest.mareuapp.di;

import com.projettest.mareuapp.service.DummyMeetingApiService;
import com.projettest.mareuapp.service.MeetingApiService;

/**
 * Dependency injector to get instance of service
 */

public class DI {

    private static final MeetingApiService apiService = new DummyMeetingApiService();

    /**
     * Get an instance on @{@link MeetingApiService}
     * @return
     */
    public static MeetingApiService getMeetingApiService() {
        return apiService;
    }

    /**
     * Get always a new instance on @{@link MeetingApiService}. only for tests
     * @return
     */
    public static MeetingApiService getNewInstanceForTest() {
        return new DummyMeetingApiService();}

}
