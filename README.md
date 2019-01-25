# shifted-span
Just hit HTTP endpoint 
GET http://localhost:8080/test
or 
GET http://localhost:8080/test?error=true

Logs example:
```text
2019-01-25 13:17:27.763 DEBUG [-,,,] 14392 --- [ctor-http-nio-2] o.s.w.s.adapter.HttpWebHandlerAdapter    : [3af2449b] HTTP GET "/test"
2019-01-25 13:17:27.868 DEBUG [-,b0b48ad9ca2a742f,b0b48ad9ca2a742f,false] 14392 --- [ctor-http-nio-2] s.w.r.r.m.a.RequestMappingHandlerMapping : [3af2449b] Mapped to public reactor.core.publisher.Mono<java.lang.String> com.example.tracedemo.TraceDemoApplication$MyController.endpointMethod(java.lang.String,boolean)
2019-01-25 13:17:28.039 DEBUG [-,b0b48ad9ca2a742f,b0b48ad9ca2a742f,false] 14392 --- [ctor-http-nio-2] o.s.w.r.r.m.a.ResponseBodyResultHandler  : Using 'text/html' given [text/html, application/xhtml+xml, image/webp, image/apng, application/xml;q=0.9, */*;q=0.8] and supported [text/plain;charset=UTF-8, text/event-stream, text/plain;charset=UTF-8, */*]
2019-01-25 13:17:28.039 DEBUG [-,b0b48ad9ca2a742f,b0b48ad9ca2a742f,false] 14392 --- [ctor-http-nio-2] o.s.w.r.r.m.a.ResponseBodyResultHandler  : [3af2449b] 0..1 [java.lang.String]
2019-01-25 13:17:28.055  INFO [-,b0b48ad9ca2a742f,ea06225f4f7c7bcb,false] 14392 --- [ctor-http-nio-2] c.e.t.TraceDemoApplication$MyController  : invoke endpointMethod([1, false])
2019-01-25 13:17:28.057  INFO [-,b0b48ad9ca2a742f,beeb2cf45824783d,false] 14392 --- [ctor-http-nio-2] c.e.t.TraceDemoApplication$MyService     : invoke serviceMethod([1, false])
2019-01-25 13:17:28.079  INFO [-,b0b48ad9ca2a742f,2f38a9e51a3ba891,false] 14392 --- [     parallel-1] c.e.t.TraceDemoApplication$MyAdapter     : invoke webclientMethod([1, false])
2019-01-25 13:17:28.152 DEBUG [-,b0b48ad9ca2a742f,db106ffbe14cb0e7,false] 14392 --- [     parallel-1] o.s.w.r.f.client.ExchangeFunctions       : [7458d420] HTTP GET http://localhost:8080/success
2019-01-25 13:17:28.213 DEBUG [-,,,] 14392 --- [ctor-http-nio-4] o.s.w.s.adapter.HttpWebHandlerAdapter    : [5c6b5a68] HTTP GET "/success"
2019-01-25 13:17:28.219 DEBUG [-,b0b48ad9ca2a742f,db106ffbe14cb0e7,false] 14392 --- [ctor-http-nio-4] s.w.r.r.m.a.RequestMappingHandlerMapping : [5c6b5a68] Mapped to public org.springframework.http.ResponseEntity<java.lang.String> com.example.tracedemo.TraceDemoApplication$MyController.success()
2019-01-25 13:17:28.224 DEBUG [-,b0b48ad9ca2a742f,db106ffbe14cb0e7,false] 14392 --- [ctor-http-nio-4] .s.w.r.r.m.a.ResponseEntityResultHandler : Using 'text/plain;charset=UTF-8' given [*/*] and supported [text/plain;charset=UTF-8, text/event-stream, text/plain;charset=UTF-8, */*]
2019-01-25 13:17:28.225 DEBUG [-,b0b48ad9ca2a742f,db106ffbe14cb0e7,false] 14392 --- [ctor-http-nio-4] .s.w.r.r.m.a.ResponseEntityResultHandler : [5c6b5a68] 0..1 [java.lang.String]
2019-01-25 13:17:28.229 DEBUG [-,b0b48ad9ca2a742f,db106ffbe14cb0e7,false] 14392 --- [ctor-http-nio-4] o.s.core.codec.CharSequenceEncoder       : [5c6b5a68] Writing "Success "
2019-01-25 13:17:28.253 DEBUG [-,b0b48ad9ca2a742f,2f38a9e51a3ba891,false] 14392 --- [ctor-http-nio-3] o.s.w.r.f.client.ExchangeFunctions       : [7458d420] Response 200 OK
2019-01-25 13:17:28.262 DEBUG [-,b0b48ad9ca2a742f,db106ffbe14cb0e7,false] 14392 --- [ctor-http-nio-4] o.s.w.s.adapter.HttpWebHandlerAdapter    : [5c6b5a68] Completed 200 OK
2019-01-25 13:17:28.303 DEBUG [-,b0b48ad9ca2a742f,2f38a9e51a3ba891,false] 14392 --- [ctor-http-nio-3] o.s.core.codec.StringDecoder             : Decoded "Success "
2019-01-25 13:17:28.309  INFO [-,b0b48ad9ca2a742f,c97f8b3210e4a147,false] 14392 --- [      elastic-2] c.e.t.TraceDemoApplication$MyAdapter     : next(Success )
2019-01-25 13:17:28.309  INFO [-,b0b48ad9ca2a742f,beeb2cf45824783d,false] 14392 --- [      elastic-2] c.e.t.TraceDemoApplication$MyService     : next(Success 1)
2019-01-25 13:17:28.309  INFO [-,b0b48ad9ca2a742f,beeb2cf45824783d,false] 14392 --- [      elastic-2] c.e.t.TraceDemoApplication$MyController  : next(Success 1)
2019-01-25 13:17:28.309 DEBUG [-,b0b48ad9ca2a742f,ea06225f4f7c7bcb,false] 14392 --- [      elastic-2] o.s.core.codec.CharSequenceEncoder       : [3af2449b] Writing "Success 1"
2019-01-25 13:17:28.310 DEBUG [-,b0b48ad9ca2a742f,2f38a9e51a3ba891,false] 14392 --- [      elastic-2] o.s.w.r.f.client.ExchangeFunctions       : [7458d420] Cancel signal (to close connection)
2019-01-25 13:17:28.321 DEBUG [-,b0b48ad9ca2a742f,b0b48ad9ca2a742f,false] 14392 --- [ctor-http-nio-2] o.s.w.s.adapter.HttpWebHandlerAdapter    : [3af2449b] Completed 200 OK
```
For invoke logs 
2019-01-25 13:17:28.055  INFO [-,b0b48ad9ca2a742f,ea06225f4f7c7bcb,false] 14392 --- [ctor-http-nio-2] c.e.t.TraceDemoApplication$MyController  : invoke endpointMethod([1, false])
2019-01-25 13:17:28.057  INFO [-,b0b48ad9ca2a742f,beeb2cf45824783d,false] 14392 --- [ctor-http-nio-2] c.e.t.TraceDemoApplication$MyService     : invoke serviceMethod([1, false])
2019-01-25 13:17:28.079  INFO [-,b0b48ad9ca2a742f,2f38a9e51a3ba891,false] 14392 --- [     parallel-1] c.e.t.TraceDemoApplication$MyAdapter     : invoke webclientMethod([1, false])

For onNext log
2019-01-25 13:17:28.309  INFO [-,b0b48ad9ca2a742f,c97f8b3210e4a147,false] 14392 --- [      elastic-2] c.e.t.TraceDemoApplication$MyAdapter     : next(Success )
2019-01-25 13:17:28.309  INFO [-,b0b48ad9ca2a742f,beeb2cf45824783d,false] 14392 --- [      elastic-2] c.e.t.TraceDemoApplication$MyService     : next(Success 1)
2019-01-25 13:17:28.309  INFO [-,b0b48ad9ca2a742f,beeb2cf45824783d,false] 14392 --- [      elastic-2] c.e.t.TraceDemoApplication$MyController  : next(Success 1)

ER: invoke entry and onNext entry for c.e.t.TraceDemoApplication$MyController logger have the same spanId=ea06225f4f7c7bcb
AR: invoke entry and onNext entry for c.e.t.TraceDemoApplication$MyController logger have different spanIds. 
onNext spanId is used from from next Span com.example.tracedemo.TraceDemoApplication.MyService.serviceMethod with spanId=beeb2cf45824783d. I.e. spans are shifted.

The same behaviour we can see for entry  
```
2019-01-25 13:17:28.309 DEBUG [-,b0b48ad9ca2a742f,ea06225f4f7c7bcb,false] 14392 --- [      elastic-2] o.s.core.codec.CharSequenceEncoder       : [3af2449b] Writing "Success 1"
```
It uses spanId from com.example.tracedemo.TraceDemoApplication.MyController.endpointMethod, which should be finished at the execution moment, but it should use initial spanId. 
