# Role-based Authorization with Active Directory for GridGain

This configuration and code example shows how to integrate GridGain's security framework with
Active Directory without any custom code, using only out-of-the-box capabilities.

The solution is described in detail here: https://medium.com/@valentin.kulichenko/role-based-authorization-with-active-directory-for-gridgain-eaad394ba173

## Build

    mvn clean package

## Start Server Node

    mvn exec:java -Dexec.mainClass=Server -Djava.security.auth.login.config=jaas.config

## Start Client Application

    mvn exec:java -Dexec.mainClass=Client
