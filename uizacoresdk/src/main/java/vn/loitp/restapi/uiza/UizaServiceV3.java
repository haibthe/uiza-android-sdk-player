package vn.loitp.restapi.uiza;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;
import vn.loitp.restapi.uiza.model.v3.UizaWorkspaceInfo;
import vn.loitp.restapi.uiza.model.v3.authentication.gettoken.ResultGetToken;
import vn.loitp.restapi.uiza.model.v3.metadata.createmetadata.CreateMetadata;
import vn.loitp.restapi.uiza.model.v3.metadata.createmetadata.ResultCreateMetadata;
import vn.loitp.restapi.uiza.model.v3.metadata.deleteanmetadata.ResultDeleteAnMetadata;
import vn.loitp.restapi.uiza.model.v3.metadata.getdetailofmetadata.ResultGetDetailOfMetadata;
import vn.loitp.restapi.uiza.model.v3.metadata.getlistmetadata.ResultGetListMetadata;
import vn.loitp.restapi.uiza.model.v3.metadata.updatemetadata.ResultUpdateMetadata;

/**
 * @author loitp
 */

public interface UizaServiceV3 {
    //http://dev-docs.uizadev.io/#get-token
    @POST("/api/public/v3/admin/user/auth")
    Observable<ResultGetToken> getToken(@Body UizaWorkspaceInfo uizaWorkspaceInfo);

    //http://dev-docs.uizadev.io/#check-token
    @POST("/api/public/v3/admin/user/auth/check-token")
    Observable<ResultGetToken> checkToken();

    //http://dev-docs.uizadev.io/#get-list-metadata
    @GET("/api/public/v3/media/metadata")
    Observable<ResultGetListMetadata> getListMetadata();

    //http://dev-docs.uizadev.io/#create-metadata
    @POST("/api/public/v3/media/metadata")
    Observable<ResultCreateMetadata> createMetadata(@Body CreateMetadata createMetadata);

    //http://dev-docs.uizadev.io/#get-detail-of-metadata
    @GET("/api/public/v3/media/metadata")
    Observable<ResultGetDetailOfMetadata> getDetailOfMetadata(@Query("id") String id);

    //http://dev-docs.uizadev.io/#update-metadata
    @PUT("/api/public/v3/media/metadata")
    Observable<ResultUpdateMetadata> updateMetadata(@Body CreateMetadata createMetadata);

    //http://dev-docs.uizadev.io/#delete-an-metadata
    @DELETE("/api/public/v3/media/metadata")
    Observable<ResultDeleteAnMetadata> deleteAnMetadata(@Query("id") String id);
}
