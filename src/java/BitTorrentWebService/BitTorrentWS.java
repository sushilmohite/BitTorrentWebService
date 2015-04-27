/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BitTorrentWebService;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author Sushil Mohite
 */
@Path("resource")
public class BitTorrentWS {

//    @Context
//    private UriInfo context;

    /**
     * Creates a new instance of BitTorrentWS
     */
    public BitTorrentWS() {
    }

    /**
     * Retrieves representation of an instance of BitTorrentWebService.BitTorrentWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getTorrents() {
        return "";
    }

    /**
     * PUT method for updating or creating an instance of BitTorrentWS
     * @param fileName
     * @param fileHash
     */
    @PUT
    @Consumes("text/plain")
    public void createTorrent(@QueryParam("fileName") String fileName, @QueryParam("fileHash") String fileHash) {
    }
}
