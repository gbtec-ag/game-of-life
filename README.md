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

## Working with GIT
* Checkout this repository from GitHub
* Create a new branch from `development`. Name your branch like the number of your Jira ticket, starting with `feat/` prefix (e.g. `feat/DES-1234`)
* Push this branch to the remote repository
* While working on the code, commit the code changes in small chunks describing your changes shortly in the commit message
* Everytime you finish a subtask, push all related commits to the remote repository

## Application
> Some Chrome Plugins are reported to block the frontend application: AdBlock, Adobe Acrobat, JSON Formatter, KeePassXC-Browser. Deactivate those or use any other browser.
* This app is split into two parts: client and server
* To start the whole application (in your IDE / IntelliJ):
  * Go to the project explorer
  * Find a file named `GameOfLifeApplication`
  * Right-click on the file
  * Select `Run 'GameOfLifeApplication'`
* After the application is started, you can reach the client in your browser via http://localhost:8081
* The client-part provides buttons `Init`, `Next`, `Play` and `Stop` which will trigger methods inside the server
* With button `Play` you have also additional integer value which should be used as a delay for continuous rendering of generations
* To have your generation printed you need to connect to the server first using the `Connect` button.
* If you have done some changes in the code, you need to restart the application and also reconnect the client by clicking `Connect` in the web UI.

## Development
* Your entry point is the file named `GameOfLifeService` inside a package named `implementation`
* As a reference, see the example implementation inside the `init` method
* Also, you have methods named `next`, `play` and `stop` available
  * Like described in the `Application` section, these methods will trigger if you click the corresponding buttons in the UI
  * You will find the base implementation of the methods in `GameOfLifeCommandProxy.java`
  * Override these methods in `GameOfLifeService.java`
* To render your generation from the UI, you need to call the `drawGeneration` method with the data which needs to be rendered
  * See example in the `init` method
  * The length and height of your generation data matrix must match (e.g. `16x16`), otherwise it will render incorrectly
* Feel free to create additional classes if it helps to organize your code
