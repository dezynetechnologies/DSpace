// /**
//  * The contents of this file are subject to the license and copyright
//  * detailed in the LICENSE and NOTICE files at the root of the source
//  * tree and available online at
//  *
//  * http://www.dspace.org/license/
//  */
package org.dspace.app.rest;

import java.util.ArrayList;
import java.util.List;

//import org.dspace.services.MilvusService;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.collection.response.ListCollectionsResp;

@RestController
@RequestMapping("/api/milvus")
public class MilvusController {

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getCollections() {
        // return new ArrayList<String>();

        // System.out.println("We are inside milvus collections.");
        // RestTemplate restTemplate = new RestTemplate();
        // String url = "http://127.0.0.1:5000/milvus";
        // String response = restTemplate.getForObject(url, String.class);

        // final Logger log = org.apache.logging.log4j.LogManager
        // .getLogger(MilvusController.class);
        // log.info(response.toString());
        // return response.toString();

        // MilvusServiceClient client = null;
        // try {
        // client = new MilvusServiceClient(ConnectParam.newBuilder()
        // .withHost("localhost")
        // .withPort(19530)
        // .build());

        // R<ShowCollectionsResponse> response =
        // client.showCollections(ShowCollectionsParam.newBuilder().build());
        // if (response != null && response.getData() != null) {
        // logger.info("Collections retrieved successfully: {}",
        // response.getData().getCollectionNamesList());
        // return response.getData().getCollectionNamesList();
        // } else {
        // logger.error("Failed to retrieve collections, response or response data is
        // null");
        // return List.of();
        // }
        // } catch (Exception e) {
        // logger.error("An error occurred while retrieving collections: ", e);
        // return List.of();
        // } finally {
        // if (client != null) {
        // client.close();
        // }
        // }
        String CLUSTER_ENDPOINT = "http://localhost:19530";

        // 1. Connect to Milvus server
        ConnectConfig connectConfig = ConnectConfig.builder().uri(CLUSTER_ENDPOINT).build();

        MilvusClientV2 client = null;
        try {
            client = new MilvusClientV2(connectConfig);
        } catch (Exception e) {
            try {
                return new ArrayList<String>();
            } catch (Exception e2) {
                System.out.println(e2);
            }

            // 5. List all collection names
            ListCollectionsResp listCollectionsRes = client.listCollections();

            System.out.println(listCollectionsRes.getCollectionNames());
            return listCollectionsRes.getCollectionNames();
        }
        return new ArrayList<String>();
        // 2. Create a collection in quick setup mode
        // CreateCollectionReq quickSetupReq = CreateCollectionReq.builder()
        // .collectionName("quick_setup")
        // .dimension(5)
        // .build();

        // client.createCollection(quickSetupReq);

        // // Thread.sleep(5000);

        // GetLoadStateReq quickSetupLoadStateReq = GetLoadStateReq.builder()
        // .collectionName("quick_setup")
        // .build();

        // Boolean res = client.getLoadState(quickSetupLoadStateReq);

        // System.out.println(res);
        // return res;
    }
}