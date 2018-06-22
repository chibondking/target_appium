# Target Mobile App (Android) and Appium Simple Shopping Cart

This is an automated test to add a product to a cart and proceed to checkout using the latest Target.com
Android native mobile app, using Appium. IntelliJ IDE (Community Edition).

The tests are written in Java and utilizes the latest JUnit libraries (v5 now called Jupiter)
Maven sources will pull down all required dependencies to run.

Pages are broken out into their own directory and common elements and actions across all pages
are stored in the BasePage object.

BaseTestClass contains all of the code to set platform capabilities for Appium, install the .APK file
if it doesn't exist (or install a newer version if one does exist). The BasePage class also contains some actions
that must be done each and every time we start a new test run. The Target mobile app has a few actions you must perform
each time the app is installed. Those are captured in the `@BeforeAll` block before executing any tests.

After all tests are run, we remove the application from the mobile device and quit.

Given some time constraints and the desire to get a MVP out the door, there are a lot of things that can be cleaned up
especially parameterization capabilities, adding environment variables, and if expanding this to capture testing
IOS and WEB platforms, working to add proper annotations within the page objects themselves and refactoring BaseTestClass
to accommodate these features.

# How you would tell if a problem exists in the server or app?
<b>Server Side</b>: If an issue exists on the server and the app (in this case mobile) does not respond or times out,
I can send a API request that is the same as what the mobile device (or other calling app - web, etc.) and evaluate the
response and response code. Digging deeper, if there's a error code 500 on the response, it then becomes necessary to
dig into the application's logs and `grep` for specific log entries named ERROR.

<b>Example</b>:

In the Target mobile app, if
I perform a search for a specific product (i.e. LaCroix), the mobile app makes a API request with my search query and
an issue occurs (somewhere) within the server stack and throws an error, the user won't see any results displayed (a
good application would inform the user that an issue has occurred). In this case, the application is "working" but it
still would be caught in testing, however the problem lies on the server side with respect to performing a function. In
this case, the issue on the app is not a functional one, rather it is non-functional. The issue on the server side is a
functional one given that it did not deliver the expected result.

<b>Application Side</b>: If an application is not behaving according to specifications and I have eliminated any possible
issues on the server (i.e. by sending the same API requests as the app would and verifying the responses) then I can
reproduce the issue and capture the requests the application is making to the server at each step and look at how the
application is proceeding through a particular user flow and the content displayed within the app.

<b>Example</b>:


If I enter in a zip code of 60606 in the Target mobile app to locate a store, and the server responds with stores within
60606 but the mobile app displays stores in 90210 then I have discovered a functional issue that resides within the app
itself.

## Is this a functional or nonfunctional issue?
* Covered above.

# If you did encounter a bug, include how you would articulate the issue in a ticket for the development team

I ran across an issue in testing the Target mobile app intermittently where the application would time out when rendering
the full SearchResultsPage, which would cause a test failure. Let's assume the following:

* The issue is a server side issue (assume that there is a bug somewhere in the search functionality)
* No network latency

Given the specs are:
- Search for Product X (default search settings is based on 'relevance', which means Product X should display first.)
- Product X should be displayed and the user should be able to click on it

My bug report would look like:

Issue: Search Results Page Displays Unexpected Error When Displaying Product

Steps To Reproduce (Target Mobile app):
(General Setup, assuming a fresh install of the app - typically you could cut this out if there's dev/qa hooks to do so)
1. Click Getting Started Button
  * Click on Anonymous Login
  * Click Search By ZipCode or Address button
  * Set Zipcode to 60606
  * Set selected store to "Chicago State St." and click Set As Store

2. Click on the search textbox and enter in the following text: "Product X" and select the first item that is displayed.

<b>Expected Result:</b>
Product X is displayed as the first result along with other products closely matching Product X according to relevance.

<b>Actual Result:</b>
Error message appears: "Oops, an unexpected error occurred. Please try again."

<b>Logs:</b>


(Side Note: Assuming this is a server side issue, related to search, I would narrow it down to the search microservice to
look for logs. Below is a simulated error on the server that I would include for relevant detail for the developer)

`com.xyz.serverSide.logger.Log [ERROR] Exception occurred in function 'parseSearchResultsAndDisplayToUser' on line: xxx
<Full Stacktrace Below>`

Note: If search functionality were 100% broken, then step 2 would fail since we wouldn't be getting any matches at all.


Appium logs can be sent to a centralized logging store to enable correlation of failed test runs in the test environment
and those in the application. That's an exercise for another day!

