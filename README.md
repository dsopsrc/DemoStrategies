# DemoStrategies
The project should help you get started with Bookmap Layer 1 API

API changes

### 13.01.2017: Strategies naming changed
Earlier to generate indicator you would provide fullName and userName to message, and fullName would be used by system to identify your indicator (like online indicator caclulations).
Now Layer1ApiUserMessageModifyIndicator constructor accepts owner class name and userName (that will be used to show indicator name in different places). Full name is generated as combination of those, and will
be unique (unless there is 2 indicators with same owner class and same user names). It can be accessed after message is created from fullName member. You should remember this generated name as it will be used
to make any requests for your indicator.

### 13.01.2017: Layer1CustomPanelsGetter#getCustomGuiFor
Now also accepts indicatorName. If strategy was selected via settings button of strategies configuration popup this will be the name of indicator (useful if you have more then 1 indicator created by strategy,
and want to show this indicators settings for example)