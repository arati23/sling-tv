/***********************************************************
 * Created by Patrick Bailey(pat.bailey@dish.com) on 8/3/17
 * Updated to include Chrome 56 on 8/9/17
 ***********************************************************/
var SlingTVUtils = (function() {

    var redirectEnabled = true;

    var whichBrowser, usrAgent, jwt;

    function jwtCookieExists() {
        jwt = decodeURIComponent(document.cookie).match(/userJwt[^;]*/);
        if( jwt && jwt.length && jwt[0].replace("userJwt=", "").length ) {
            return true;
        }
        return false;
    }

    function setBrowser(s) {
        if (typeof s === "string" && s) {
            usrAgent = s;
        } else {
            usrAgent = typeof navigator.userAgent === "string" ? navigator.userAgent : 'unknown-broswer/v1.0';
        }
        whichBrowser = usrAgent.match(/(MSIE|(?!Gecko.+)Firefox|(?!AppleWebKit.+Chrome.+)Safari|(?!AppleWebKit.+)Chrome|AppleWebKit(?!.+Chrome|.+Safari)|Gecko(?!.+Firefox))(?: |\/)([\d\.apre]+)/);
        return whichBrowser;
    }

    function browserType() {
        setBrowser();
        if( typeof whichBrowser === "object" && whichBrowser && whichBrowser.length > 1 ) {
            return whichBrowser[1];
        }
        return 'unknown-browser';
    }

    function browserVersion() {
        setBrowser();
        if( typeof whichBrowser === "object" && whichBrowser && whichBrowser.length > 2 ) {
            if( parseInt(whichBrowser[2]) != NaN ) {
                return parseInt(whichBrowser[2]);
            }
        }
        return 0;
    }

    function isHomePage() {
        if( location.hostname === "localhost" && location.pathname === "/content/sling-tv/en/domestic.html" ) {
            return true;
        }
        return ( location.pathname == "/" );
    }


    function browserPlayerHost() {
        var ret = null;
        switch( location.hostname ) {
            case 'www.d.sling.com' : ret = 'watch.d.sling.com'; break;
            case 'www.q.sling.com' : ret = 'watch.q.sling.com'; break;
            case 'www.b.sling.com' : ret = 'watch.b.sling.com'; break;
            case 'www.sling.com' : ret = 'watch.sling.com'; break;
            default: ret = null; break;
        }
        if( !ret ) {
            alert('redirect to watch.sling.com would have occured in a different environment');
        }
        return ret;
    }

    function conditionalRedirectToBrowserPlayer() {
        if( redirectEnabled ) {
            try {
                if (isHomePage() && browserType() === 'Chrome' && browserVersion() > 55 && jwtCookieExists() && browserPlayerHost()) {
                    if( document.referrer.search('billing_browser_player_preview') != -1 ) {
                        window.location = document.referrer;
                    } else {
                        window.location = "https://" + browserPlayerHost();
                    }
                }
            } catch (e) {
                console.log(e);
            }
        }
    }

    return {
        conditionalRedirectToBrowserPlayer: conditionalRedirectToBrowserPlayer,
        browserType: browserType,
        browserVersion: browserVersion,
        setBrowser: setBrowser,
        jwtCookieExists: jwtCookieExists,
        isHomePage : isHomePage
    };

})();