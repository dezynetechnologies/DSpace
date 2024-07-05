// /**
//  * The contents of this file are subject to the license and copyright
//  * detailed in the LICENSE and NOTICE files at the root of the source
//  * tree and available online at
//  *
//  * http://www.dspace.org/license/
//  */
package org.dspace.app.rest;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.dspace.app.rest.converter.ConverterService;
import org.dspace.app.rest.exception.UnprocessableEntityException;
import org.dspace.app.rest.link.HalLinkService;
import org.dspace.app.rest.model.FacetConfigurationRest;
import org.dspace.app.rest.model.FacetResultsRest;
import org.dspace.app.rest.model.SearchConfigurationRest;
import org.dspace.app.rest.model.SearchResultsRest;
import org.dspace.app.rest.model.SearchSupportRest;
import org.dspace.app.rest.model.hateoas.FacetConfigurationResource;
import org.dspace.app.rest.model.hateoas.FacetResultsResource;
import org.dspace.app.rest.model.hateoas.FacetsResource;
import org.dspace.app.rest.model.hateoas.SearchConfigurationResource;
import org.dspace.app.rest.model.hateoas.SearchResultsResource;
import org.dspace.app.rest.model.hateoas.SearchSupportResource;
import org.dspace.app.rest.parameter.SearchFilter;
import org.dspace.app.rest.repository.DiscoveryRestRepository;
import org.dspace.app.rest.utils.Utils;
//import org.dspace.services.MilvusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.milvus.*;
import io.milvus.client.MilvusClient;
import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.ShowCollectionsResponse;
import io.milvus.param.ConnectParam;
import io.milvus.param.R;
import io.milvus.param.collection.ShowCollectionsParam;
import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.collection.request.CreateCollectionReq;
import io.milvus.v2.service.collection.request.GetLoadStateReq;
import io.milvus.v2.service.collection.response.ListCollectionsResp;

@RestController
@RequestMapping("/api/milvus")
public class MilvusController{


    private static final Logger logger = LoggerFactory.getLogger(MilvusController.class);
    @RequestMapping(method = RequestMethod.GET)
    public List<String> getCollections() {
        //return new ArrayList<String>();
        System.out.println("We are inside milvus collections.");
       // System.out.println(milvusService.listCollections());
        //return milvusService.listCollections();

        // MilvusServiceClient client = null;
        // try {
        //     client = new MilvusServiceClient(ConnectParam.newBuilder()
        //             .withHost("localhost")
        //             .withPort(19530)
        //             .build());
            
        //     R<ShowCollectionsResponse> response = client.showCollections(ShowCollectionsParam.newBuilder().build());
        //     if (response != null && response.getData() != null) {
        //         logger.info("Collections retrieved successfully: {}", response.getData().getCollectionNamesList());
        //         return response.getData().getCollectionNamesList();
        //     } else {
        //         logger.error("Failed to retrieve collections, response or response data is null");
        //         return List.of();
        //     }
        // } catch (Exception e) {
        //     logger.error("An error occurred while retrieving collections: ", e);
        //     return List.of();
        // } finally {
        //     if (client != null) {
        //         client.close();
        //     }
        // }
        String CLUSTER_ENDPOINT = "http://localhost:19530";
        final String LOG_FILE_PATH = "/home/mirae/Projects/milvus_logs.txt";
        List<String> logs = new ArrayList<>();

        // 1. Connect to Milvus server
        ConnectConfig connectConfig = ConnectConfig.builder().uri(CLUSTER_ENDPOINT).build();
    
        MilvusClientV2 client = null;
        try{
        client = new MilvusClientV2(connectConfig);
        }catch(Exception e){
            try{    
                FileWriter fw=new FileWriter(LOG_FILE_PATH);    
                fw.write(e.getMessage());    
                fw.close();
                return new ArrayList<String>(); 
               }catch(Exception e2){System.out.println(e2);}  
        }
        // // 5. List all collection names
        //     ListCollectionsResp listCollectionsRes = client.listCollections();

        //     System.out.println(listCollectionsRes.getCollectionNames());
        //     logs.add("Collections: " + listCollectionsRes.getCollectionNames());
        //     logs.add("Exception: " + e.getMessage());
        //     for (StackTraceElement ste : e.getStackTrace()) {
        //         logs.add(ste.toString());
        //     }
        //     File logFile = new File(LOG_FILE_PATH);
        //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile))) {
        //         for (String log : logs) {
        //             writer.write(log);
        //             writer.newLine();
        //         }
        //     } catch (IOException e2) {
        //         e2.printStackTrace();
        //     }
        //     return listCollectionsRes.getCollectionNames();
        // }
        return new ArrayList<String>();
        // 2. Create a collection in quick setup mode
        // CreateCollectionReq quickSetupReq = CreateCollectionReq.builder()
        //     .collectionName("quick_setup")
        //     .dimension(5)
        //     .build();

        // client.createCollection(quickSetupReq);

        // // Thread.sleep(5000);

        // GetLoadStateReq quickSetupLoadStateReq = GetLoadStateReq.builder()
        //     .collectionName("quick_setup")
        //     .build();

        // Boolean res = client.getLoadState(quickSetupLoadStateReq);

        // System.out.println(res);
        // return res;
    }

}