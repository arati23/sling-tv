import javax.jcr.Node
import javax.jcr.Session

/*
*Usage - please do  the following before executing this script
*
*-->nodepath - provide the parent path of the node, will iterate all the nodes and filter the matching node for the given resource type
*
*-->packageIDVal - provide the value for the Package ID
*
*-->extrasVal - provide the value for the extras (you can add multiple values separed by ",")
*
*-->propExtrasNotExist (true/false) -- if "true", execute even if the property "extras" doesn't exist in the current CTA
*
*-->skipExtras (true/false) -- if "true", Skip property "Extras" during the execution (means no action on this property)
*
*-->propPackIdNotExist (true/false) -- if "true", execute even if the property "Package" doesn't exist in the current CTA
*
*-->skipPackId (true/false) -- if "true", Skip property "Package" during the execution (means no action on this property)
*
*-->invalidPaths - Provide the path of CTA/Page if you want to exclude any CTA/Page node during the execution (you can add as many as separated by comma ","
*you keep empty if nothing is there (def invalidPaths = [""] as ArrayList)
*
*/

def nodePath="/content/experience-fragments/sling/international/cta/offer-and-details/master"
def packageIDVal=""
def extrasVal=""
def propExtrasNotExist=true
def skipExtras=false
def propPackIdNotExist=true
def skipPackId=false
def invalidPaths = [""] as ArrayList
//def invalidPaths = ["<page/ctapath>", "<page/ctapath>","<page/ctapath>"] as ArrayList


def resourcePath="sling-tv/components/core/cta"

getNode(nodePath).recurse { resourceNode ->

if((resourceNode.path).contains("root/cta") && !invalidPaths.contains(resourceNode.path)){

    if (resourceNode.hasProperty('sling:resourceType')) {
        
        final def resourceType = resourceNode.getProperty('sling:resourceType').string
        
        if (resourceType.equals(resourcePath)) {
            
            updateExtras=0;
            updatePackageId=0;
            
            if (!skipExtras && (resourceNode.hasProperty('ats') || propExtrasNotExist)) {
                 resourceNode.setProperty('ats', extrasVal)
                 resourceNode.save();
                 updateExtras++;
                
            }
            if (!skipPackId && (resourceNode.hasProperty('sb') || propPackIdNotExist)) {
                
                 resourceNode.setProperty('sb', packageIDVal)
                 resourceNode.save();
                 updatePackageId++;
                
            }
          
          if(updateExtras==1 && updatePackageId==1){
              
               println "Updated Package ID and Extras at<-->"+resourceNode.path
              
          }else if(updateExtras==0 && updatePackageId==0){
              
               println "No action on CTA node at<-->"+resourceNode.path
               
          }else if(updateExtras==1 && updatePackageId==0){
              
               println "Updated Extras at<-->"+resourceNode.path
               
          }else if(updateExtras==0 && updatePackageId==1){
              
               println "Updated Package ID at<-->"+resourceNode.path
          }
           
             
        }
    }
  }
}