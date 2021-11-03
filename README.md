<p>
  <img src="https://www.browserstack.com/images/static/header-logo.jpg" width="200" align="left" />  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;WebRTC Testing
  <img src="https://www.gstatic.com/devrel-devsite/prod/v328e217e4b751c25f062fe5c7682def5a7a55ca15e8bc2c276cdea0c86a8ee13/webrtc/images/lockup.svg" width="120" align="right" /> 
</p>

---

This repository showcases automated testing of WebRTC-based web application on both Desktop and Mobile Browsers by executing the tests on Browserstack.

## Repository setup

- Clone the repository

- Ensure you have the following dependencies installed on the machine

- Java >= 8

- Maven >= 3.1+

Maven:

```sh

mvn install -DskipTests

```

## Browser combinations in this repository

This repository showcases running of WebRTC based application on Browserstack on different browser combinations based on user selection as follows:

| User Selection  | Description  |
| ------------ | ------------ |
| 1  | Run WebRTC test on Google Chrome and Firefox Browser  |
| 2 |  Run WebRTC test on Edge and Safari Browser  |
| 3  | Run WebRTC test on Android and iOS |

---

## BrowserStack

[BrowserStack](https://browserstack.com) provides instant access to 2,000+ real mobile devices and browsers on a highly reliable cloud infrastructure that effortlessly scales as testing needs grow.

### Prerequisites

- Create a new [BrowserStack account](https://www.browserstack.com/users/sign_up) or use an existing one.

- Identify your BrowserStack username and access key from the [BrowserStack Automate Dashboard](https://automate.browserstack.com/) and export them as environment variables using the below commands.

- For \*nix based and Mac machines:
  ```sh
  export BROWSERSTACK_USERNAME=<browserstack-username> &&
  export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```
* For Windows:
  * For CMD:
   
      ``` shell
      set BROWSERSTACK_USERNAME=<browserstack-username>
      set BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
      ```
      
  * For Powershell(Setting Environment Variable Persistently):
      
      ``` shell
      [System.Environment]::SetEnvironmentVariable('BROWSERSTACK_USERNAME','<browserstack-username>')
      [System.Environment]::SetEnvironmentVariable('BROWSERSTACK_ACCESS_KEY','<browserstack-access-key>')
      ```

  Alternatively, you can also hardcode username and access_key in the [ WebRTCTestRunner.java](src/main/java/WebRTCTestRunner.java) file.

## Running Your Tests
