PhoneStateChangeListener
========================
**developed under cordova 2.6 and tested with Android Api level 17**

Cordova plugin to provide callback when Telephony state changes

Possible states are IDLE, OFFHOOK and RINGING

# to use:

**1** - Import the plugin right after your cordova.js file:

    <script src="plugins.PhoneStateChangeListener.js"></script>
    
Also register in res/xml/config.xml:

    <plugin name="PhoneStateChangeListener" value="com.szanata.cordova.plugins.PhoneStateChangeListener"/>
    
And import the .java to your project

    com/szanata/cordova/plugins/PhoneStateChangeListener.java

**2** - Setup the callback to handle when state changes:
    
    plugins.PhoneStateChangeListener.start(function (state){
      // do stuff with the state
    });
    
**3** - Possible values to 'state' variable are:

    plugins.PhoneStateChangeListener.IDLE // when no call is being made
    plugins.PhoneStateChangeListener.RINGING // when the phone is ringing
    plugins.PhoneStateChangeListener.OFFHOOK // when the phone is during a call conversation
    plugins.PhoneStateChangeListener.NONE // when something goes wrong
    
**4** - To stop listening to this:

    plugins.PhoneStateChangeListener.stop();
    
    
# changelog
 
### version 1.1
- Added second parameter to retrieve incoming call number (when state is RINGING)
- Fixed indentation
- Added some code optimizations and hardiness

### version 1
- Added basic support to listen when Telephony state changes
- Added feature to remove this listener
- Full compatibility with cordova 2.6
