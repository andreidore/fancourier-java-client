[FanCourier](https://www.fancourier.ro) client for java

Table of Contents
=================

* [Maven](#maven)
* [Usage](#usage)
* [Todo](#todo)

# Maven #

FanCourier client is now in maven central repo.

```xml
<dependency>
    <groupId>com.github.andreidore</groupId>
    <artifactId>fancourierclient</artifactId>
    <version>0.1.0</version>
</dependency>
```

# Usage

Create client
```java
FanCourierClient client = new FanCourierClient("username", "client_id","password");
```

Get cities
```java
List<String[]> cities=client.getCitiesRaw();
or
Map<String,Set<City>> cities=client.getCities();

```



# Todo

* ~~GetCities~~
* Generate awb
* Download awb
