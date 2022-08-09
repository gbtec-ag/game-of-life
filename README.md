## Preparation

* Log into [GitHub](https://github.com/gbtec-ag) and [Jira](https://gbtecag.atlassian.net/) using the invitations you received by email
* Download and install [IntelliJ Community](https://www.jetbrains.com/idea/download/#section=windows)
* Go to IntelliJ Settings
  * Go to "Plugins" and install "Adapter for Eclipse Code Formatter"
  * Go to "Tools" > "Actions on Save"
    * Enable "Reformat code"
    * Enable "Optimize imports"
  * Go to "Other Settings" > "Adapter for Eclipse Code Formatter"
    * Enable "Use Eclipse's Code Formatter"
    * Download and use [gbtec-formatter](https://github.com/gbtec-ag/biccloud-dev-tools/blob/master/eclipse/preferences/Java/Code%20Style/Formatter/gbtec-formatter.xml) in "Eclipse formatter config" > "Eclipse workspace/project folder or config file"
    * Enable "Optimize Imports"
    * Download and use [gbtec.importorder](https://github.com/gbtec-ag/biccloud-dev-tools/blob/master/eclipse/preferences/Java/Code%20Style/Organize%20Imports/gbtec.importorder) in "Import Order from file"

## Learning path

1. You can learn Java basics at:  
https://www.codecademy.com/learn/learn-java
2. Find good information about John Conway's Game of Life in the internet and understand the rules

## Development

* Checkout this repository
* Change to branch `template`
* Create your own branch named after your Jira ticket and prefix it with `feat/` (e.g. `feat/DES-1234`)
* Publish that branch
* Everytime you finish a subtask, push all related commits

## Running the application

* Run with `mvn clean package && java -jar target/game-of-life-1.0.0-SNAPSHOT.jar`
* Stop with `Ctrl+C`
