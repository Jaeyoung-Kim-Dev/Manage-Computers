# Manage Computers

Secure Java Programming

run this command below in source folder

#### For Linux and macOS:

> java -cp .:Domain.jar -Djava.security.manager -Djava.security.policy=security.policy ManageComputers

#### For Windows:

> java -cp .;Domain.jar -Djava.security.manager -Djava.security.policy=security.policy ManageComputers

### Security.Policy

> grant codeBase "file:/root/ManageComputers/\*" {
> permission java.io.FilePermission "/root/assign1Data", "read,write";
> };

> grant codeBase "file:/root/ManageComputers/\*" {

    permission java.io.FilePermission "/root/assign1Data/*", "read,write,delete";

};

dd
