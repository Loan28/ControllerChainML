# video-chain

How to run the controller:

    -Go in the folder Controller/out/artifacts/Controller
    -Run from the terminal "java -jar chainML.jar"

How to setup the controller: 

For the IP and port of the controller : Go to the file Controller/out/artifacts/Controller/config/controller.json
To setup the framework go to : Controller/out/artifacts/Controller/config/
  - Add a json file named application000X (where x is an number). 
  - In an application file you can set up the following things : 
  - Take a look at application0001.json for an example and documentation
To setup the devices : Go to the file Controller/out/artifacts/Controller/config/device.json

Models available and their labels: 
  - face_detection.pb & face_label.txt
  - deeplabv3_257.tflite & labeltest.txt
  - posenet_mobilenet_100_257_257.tflite & none
  - dog_breed.lite & dog_label.txt
  - mobilenet.tflite & label_ILSVRC-2012-CLS.txt
  - efficientnet.tflite & label_ILSVRC-2012-CLS.txt
  - resnet_model_v2 & label_ILSVRC-2012-CLS.txt
  - inception_v1 & label_ILSVRC-2012-CLS.txt
  
How to run the framework:  
    -Go in the folder chainML/out/out/chainML_main_jar/
    -Run from the terminal "java -jar chainML.main.jar"
    
How to run the application on android:
   - Install the app from Android studio
   - Press on the "start server" button
