# pd-migrate-PBKDF2

Given a salt, hash and number of iterations, will rewrite a PBKDF2 password into the following format, for storage in PingDirectory:

```
{PBKDF2}ABizgkmOYhWHD6vvFtAcYcLp24MSwoxpUN4D6F06UDxDw+IC+jQQ41wTJy+Ta0CTu3wb4g==
```

## Compile It

```
javac convertPassword.java
```

## See the syntax

```
java convertPassword <base64Salt> <encodedPassword> <rounds>
```

## Rewrite a password

```
java convertPassword s4JJjmIVhw+r7xbQHGHC6duDEsKMaVDe XTpQPEPD4gL6NBDjXBMnL5NrQJO7fBvi 1000
```