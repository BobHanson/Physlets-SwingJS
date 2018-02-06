package faraday;

interface Fluxable{
    void setShowCurrentArrow(boolean show);
    double doFluxIntegral();    // return the flux
    double getFlux();
    double getVolt();
    void reset();
}
