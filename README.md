# Micro Frontends Example
Example of Micro Frontends in GWT and DominoUI

Here are the microfrontends:

# Microfrontends Order (Standalone)

![Microfrontends Order](microfrontends-order/microfrontends-order-client/src/doc/microfrontends-order.png?raw=true "Microfrontends Order")

# Microfrontends Support (Standalone)

![Microfrontends Support](microfrontends-support/microfrontends-support-client/src/doc/microfrontends-support.png?raw=true "Microfrontends Support")

# Microfrontends Platform (Integration of Order and Support)

![Microfrontends Platform](microfrontends-platform/microfrontends-platform-client/src/doc/microfrontends-platform.png?raw=true "Microfrontends Platform")

To run the microfrontends you need to start each Maven project separately. Each project will be started in different port number.

Go to each Maven project

- Order: microfrontends-order/microfrontends-order-client
- Support: microfrontends-support/microfrontends-support-client
- Platform: microfrontends-platform/microfrontends-platform-client

... and run:

```
mvn gwt:generate-module gwt:devmode
```