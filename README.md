<h1 align="center">Welcome to Manage Computers 👋</h1> 

Secure Java Programming

run this command below in source folder

#### For Linux and macOS:

```sh
> java -cp .:Domain.jar -Djava.security.manager -Djava.security.policy=security.policy ManageComputers
```

#### For Windows:

```sh
> java -cp .;Domain.jar -Djava.security.manager -Djava.security.policy=security.policy ManageComputers
```

### Security.Policy

```java
grant codeBase "file:/root/ManageComputers/\*" {
    permission java.io.FilePermission "/root/assign1Data", "read,write";
};
grant codeBase "file:/root/ManageComputers/\*" {
    permission java.io.FilePermission "/root/assign1Data/*", "read,write,delete";
;
```

## Author

👤 **Jaeyoung Kim**

- Website: https://www.jaeyoungkim.ca/
- Github: [@jaeyoung-kim-dev](https://github.com/jaeyoung-kim-dev)
- LinkedIn: [@jaeyoung-kim-dev](https://www.linkedin.com/in/jaeyoung-kim-dev/)
- Medium(Blog): [@jaeyoung-kim-dev](https://jaeyoung-kim-dev.medium.com/)
- Email: jaeyong.kim.dev@gmail.com
