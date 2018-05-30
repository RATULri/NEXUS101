package nexus101.network.uploads;

import android.util.Log;

import java.util.List;

import nexus101.network.ApiClient;
import nexus101.network.responses.InsertResponse;
import nexus101.network.services.GroupInsertApiInterface;
import nexus101.network.services.StudentAttendanceApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceUpload {

    AttendanceInsertCallback mCallback;
    StudentAttendanceApiInterface apiService;

    public AttendanceUpload(AttendanceInsertCallback attendanceInsertCallback){
        this.mCallback = attendanceInsertCallback;
    }

    public void run(int course_id, List<Integer> student_id, List<Integer> isPresent, String date){

        apiService = ApiClient.getClient().create(StudentAttendanceApiInterface.class);
        Call<InsertResponse> call = apiService.storeAttendance(course_id, student_id, isPresent, date);

        call.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(Call<InsertResponse> call, Response<InsertResponse> response) {
                Log.d("Test", response.body().toString());
                if (response.body().getStatus().equals(1)){
                    mCallback.onAttendanceInsertSuccess();
                }
                else {
                    mCallback.onAttendanceInsertError();
                }

            }

            @Override
            public void onFailure(Call<InsertResponse> call, Throwable t) {
                mCallback.onAttendanceInsertError();
            }
        });

    }

}
