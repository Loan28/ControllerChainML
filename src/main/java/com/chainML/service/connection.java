package com.chainML.service;

import com.google.gson.*;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
import java.lang.String;

public class connection {


    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {

        JsonParser parser = new JsonParser();
        String model = "";
        String label = "";
        String nextDevice = "";
        String model_type = "";
        String input = "";
        String inputType = "";
        String condition = "";
        String condition2 = "";
        String action = "";
        String action2 = "";
        String applicationType;
        String id = "";

        DiskFileStore imageStore = new DiskFileStore("files");
        Object controllerJson = parser.parse(new FileReader("config/controller.json"));
        Object deviceJson = parser.parse(new FileReader("config/device2.json"));
        JsonObject jsonObjectDevice = (JsonObject) deviceJson;
        JsonArray listDevice = (JsonArray) jsonObjectDevice.get("device");

        controllerClient Devices[] = new controllerClient[listDevice.size()];

        for(int i = 0; i < listDevice.size();i++){
            Devices[i] = new controllerClient(listDevice.get(i).getAsJsonObject().get("ip").getAsString(),listDevice.get(i).getAsJsonObject().get("port").getAsInt());
        }

        String ipController = ((JsonObject) controllerJson).get("controller").getAsJsonObject().get("ip").getAsString();
        int portController = ((JsonObject) controllerJson).get("controller").getAsJsonObject().get("port").getAsInt();
        controllerServer server = new controllerServer(portController,imageStore);
        server.start();

        Scanner sc= new Scanner(System.in);
        String command = "";

        do {
            System.out.print("\nTo deploy ML algorithms type in deploy\nTo end the program press x\n\n");
            command = sc.nextLine();
            if(command.equals("deploy")) {    // deploys the application stored in a json file
                System.out.print("Enter application ID(s): \n\n");
                command = sc.nextLine();
                String[] appIDArray = command.split(" ");
                //
                //Loop through the number of app we want to run
                for(int c = 0; c < appIDArray.length; c++){
                    Object AppJson = parser.parse(new FileReader("config/application"+ appIDArray[0] +".json"));
                    JsonObject jsonObjectApp = (JsonObject) AppJson;
                    JsonArray ListApp = (JsonArray) jsonObjectApp.get("application");
                    //
                    //Search for the app in the JSON file that correspond to the app given by the user
                    input = ListApp.get(0).getAsJsonObject().get("input").getAsString();
                    inputType = ListApp.get(0).getAsJsonObject().get("inputType").getAsString();
                    applicationType = ListApp.get(0).getAsJsonObject().get("applicationType").getAsString();
                    JsonArray ListDeploy = (JsonArray) ((JsonArray) jsonObjectApp.get("application")) //Array that contains information for the deployment
                            .get(0).getAsJsonObject()
                            .get("deployment");
                    //
                    // Retrieve information for each devices and assign to the
                    for (int j = 0; j < ListDeploy.size(); j++) {
                        model = ListDeploy.get(j)
                                .getAsJsonObject()
                                .get("model").getAsString();
                        label = ListDeploy
                                .get(j).getAsJsonObject()
                                .get("label").getAsString();
                        model_type = ListDeploy.get(j).getAsJsonObject()
                                    .get("modelType").getAsString();
                        nextDevice = ListDeploy
                                .get(j).getAsJsonObject()
                                .get("nextDevice").getAsString();
                        condition = ListDeploy
                                .get(j)
                                .getAsJsonObject()
                                .get("condition").getAsString();
                        condition2 = ListDeploy
                                .get(j)
                                .getAsJsonObject()
                                .get("condition2").getAsString();
                        action = ListDeploy
                                .get(j)
                                .getAsJsonObject()
                                .get("action").getAsString();
                        action2 = ListDeploy
                                .get(j)
                                .getAsJsonObject()
                                .get("action2").getAsString();
                        id = ListDeploy
                                .get(j)
                                .getAsJsonObject()
                                .get("location").getAsString();

                        //
                        //Assign to the actions and nextdevice variable the ip addresses corresponding to the location given in the json file
                        for(int k = 0; k < listDevice.size(); k++ ){
                            if(nextDevice.equals(listDevice.get(k).getAsJsonObject().get("id").getAsString())){
                                nextDevice = listDevice.get(k).getAsJsonObject().get("ip").getAsString();
                            }
                            if(action.equals(listDevice.get(k).getAsJsonObject().get("id").getAsString())){
                                action = listDevice.get(k).getAsJsonObject().get("ip").getAsString();
                            }
                            if(action2.equals(listDevice.get(k).getAsJsonObject().get("id").getAsString())){
                                action2 = listDevice.get(k).getAsJsonObject().get("ip").getAsString();
                            }
                        }
                        Devices[j].DeployAlgo(model, label,model_type, condition, condition2, action, action2, nextDevice,applicationType, id, ipController, portController, Devices[j]);
                    }

                    //
                    //Find the first device in the app and upload the input to this device
                    for (int i = 0; i < ListDeploy.size(); i++) {
                        if(ListDeploy.get(i).getAsJsonObject().get("first").getAsString().equals("1")){
                            Devices[i].uploadFile("input/" + input, inputType);
                        }
                    }
                }
            }
        }while((!"x".equals(command)));
        server.stop();
        for (int i = 0; i < listDevice.size(); i++) {
            Devices[i].shutdown();
        }
    }



}
