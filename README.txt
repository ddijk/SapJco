Specify location of librfc32.dll and sapjcorfc.dll to JVM:

-Djava.library.path=E:\users\dick\workspace-mob\Arasoft

and sapjco.jar to JVM Classpath



Exception in thread "main" java.lang.ExceptionInInitializerError: JCO.classInitialize(): Could not load middleware layer 'com.sap.mw.jco.rfc.MiddlewareRFC'
JCO.nativeInit(): Could not initialize dynamic link library sapjcorfc [C:\Users\burianel\Documents\workspace_tools\SapJcoTool\sapjcorfc.dll: Can't find dependent libraries]. java.library.path [.]
        at com.sap.mw.jco.JCO.<clinit>(JCO.java:776)
        at nl.common.SapClient.<init>(SapClient.java:51)
        at nl.mybrand.don.OrderDetailTest.main(OrderDetailTest.java:16)

Exception in thread "main" java.lang.ExceptionInInitializerError: JCO.classInitialize(): Could not load middleware layer 'com.sap.mw.jco.rfc.MiddlewareRFC'
JCO.nativeInit(): Could not initialize dynamic link library sapjcorfc: Can't find dependent libraries]. java.library.path [.]
