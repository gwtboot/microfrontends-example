# Microfrontends Examples

**Example of Micro Frontends in GWT and DominoUI**

Here are the microfrontends:

## Microfrontends Order (Standalone)

![Microfrontends Order](microfrontends-order/microfrontends-order-client/src/doc/microfrontends-order.png?raw=true "Microfrontends Order")

## Microfrontends Support (Standalone)

![Microfrontends Support](microfrontends-support/microfrontends-support-client/src/doc/microfrontends-support.png?raw=true "Microfrontends Support")

## Microfrontends Platform (Integration of Order and Support)

![Microfrontends Platform](microfrontends-platform/microfrontends-platform-client/src/doc/microfrontends-platform.png?raw=true "Microfrontends Platform")

# Run the Examples

To run the microfrontends you need to start GWT devmode separately for each GWT Maven project. 
Each project will be started in different port number for Jetty and GWT CodeServer.

Go to each Maven project

- **Order**: microfrontends-order/microfrontends-order-client
- **Support**: microfrontends-support/microfrontends-support-client
- **Platform**: microfrontends-platform/microfrontends-platform-client

... and run each project with:

```
mvn gwt:generate-module gwt:devmode
```

So you will have three GWT devmode instances. After that just "Copy to Clipboard" the web app address into your web browser
and run it just like the screenshots above.
