package com.ouyang.qingque.qingqueservice;

import com.ouyang.qingque.model.FoucsModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fengyun on 2018/3/27.
 */

public interface QingQueService {
    //根据用户获取关注
    @GET("zone/userNote/getMyConcernUsersNotes.htmls")
    Observable<FoucsModel> getFocusList
    (@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("theLastDateTime") String theLastDateTime,@Query("device") int device
    ,@Query("appKey") int appKey,@Query("machineCode") String machineCode,@Query("versionCode") int versionCode,@Query("token") String token,@Query("uid") int uid);
}
