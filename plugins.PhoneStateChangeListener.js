/**
 * 	PhoneStateChangeListener cordova plugin (Android)
 * 
 * 	@author Stéfano Stypulkows Zanata
 * 	@see http://szanata.com
 *  @reference https://github.com/madeinstefano/PhoneStateChangeListener
 * 	@license MIT <http://szanata.com/MIT.txt>
 * 	@license GNU <http://szanata.com/GNU.txt>
 *  
 * 	Based upon PhoneListener by authored by Tommy-Carlos Williams <https://github.com/devgeeks>
 * 
 */
window.plugins = window.plugins || {};
window.plugins.PhoneStateChangeListener = {
  RINGIGNG: 'RINGING',
  OFFHOOK: 'OFFHOOK',
  IDLE: 'IDLE',
  NONE: 'NONE',
  start: function(callback) {
    cordova.exec(callback,function (){},'PhoneStateChangeListener','start',[]);
  },
  stop: function(callback) {
    cordova.exec(callback, function (){}, 'PhoneStateChangeListener', 'stop',[]);
  }
};
