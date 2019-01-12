import javax.jcr.Node
import javax.jcr.Session

/*
*Usage - please do  the following before executing this script
*
*-->nodepath - provide the parent path of the node, will iterate all the nodes and filter the matching node for the given resource type

*-->skipExtras (true/false) -- if "true", Skip property "Extras" during the execution (means no action on this property)
*
*-->skipPackId (true/false) -- if "true", Skip property "Package" during the execution (means no action on this property)
*
*-->invalidPaths - Provide the path of CTA/Page if you want to exclude any CTA/Page node during the execution (you can add as many as separated by comma ","
*you keep empty if nothing is there (def invalidPaths = [""] as ArrayList)
*
*/

def nodePath="/content/experience-fragments/sling/international/cta/offer-and-details/master"
def skipExtras=false
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
            
            if (!skipExtras && resourceNode.hasProperty('ats')) {
                 resourceNode.getProperty('ats').remove()
                 resourceNode.save();
                 updateExtras++;
                
            }
            if (!skipPackId && resourceNode.hasProperty('sb')) {
                
                 resourceNode.getProperty('sb').remove()
                 resourceNode.save();
                 updatePackageId++;
                
            }
          
          if(updateExtras==1 && updatePackageId==1){
              
               println "Removed Package ID and Extras at<-->"+resourceNode.path
              
          }else if(updateExtras==0 && updatePackageId==0){
              
               println "No action on CTA node at<-->"+resourceNode.path
               
          }else if(updateExtras==1 && updatePackageId==0){
              
               println "Removed Extras on CTA Node<-->"+resourceNode.path
               
          }else if(updateExtras==0 && updatePackageId==1){
              
               println "Removed Package ID on CTA Node<-->"+resourceNode.path
          }
           
             
        }
    }
  }
}	