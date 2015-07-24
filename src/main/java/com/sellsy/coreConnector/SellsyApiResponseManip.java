/**
 * 
 */
package com.sellsy.coreConnector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * A collection of static method to manipulate Sellsy ApiResponse. To be enhanced
 * 
 * @author yves
 * 
 */
public class SellsyApiResponseManip {

    private static final Logger logger = LoggerFactory.getLogger(SellsyApiResponseManip.class);

    /**
     * Extract as a list all object response from a multi valued response
     * 
     * @param response
     * @return
     */
//    @SuppressWarnings("unchecked")
    public static List<SellsyApiResponse> extractResponseList(SellsyApiResponse response) throws SellsyApiException {

        Map<String, Object> infos = (Map<String, Object>) response.getResponseAttribute("infos");
        if (infos == null)
            throw new SellsyApiException("Response is not a list : " + response.toString());
        else {
            List<SellsyApiResponse> toReturn = new ArrayList<>();

            try {
                Map<String, Object> responseList = (Map<String, Object>) response.getResponseAttribute("result");

                for (String id : responseList.keySet()) {
                    toReturn.add(new SellsyApiResponse((Map<String, Object>) responseList.get(id)));
                }
                
                // ClassCast Exception is raised if result is void
            } catch (ClassCastException e) {
            }

            return toReturn;
        }
    }

    /**
     * Flat converts info found in the response to return a new object of class type Returned object
     * will get response content as follows : response must have a string attribute attr for which
     * type has a setAttr method.
     * 
     * @param response
     * @param typedObject
     * @return
     */
    public static Object convert(SellsyApiResponse response, Class<? extends Object> type) throws SellsyApiException {

        Object toReturn = null;

        // if (logger.isDebugEnabled())
        // logTypeMethods(type);

        try {
            toReturn = type.newInstance();
            for (String attribute : response.getAttributesList()) {

                Method setter = retrieveSetter(attribute, type);
                if (setter != null) {
                    Object[] args = new Object[1];
                    args[0] = response.getResponseAttribute(attribute);
                    setter.invoke(toReturn, args);
                }

            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.error("unable to convert {} to type {} : {}", response, type.getName(), e.toString());
        }

        return toReturn;

    }

    @SuppressWarnings("unused")
    private static void logTypeMethods(Class<? extends Object> type) {
        logger.debug(String.format("Asking sellsy response conversion to type %s", type.getName()));
        for (Method method : type.getMethods()) {
            logger.debug(String.format("  Method %s", method.getName()));
            int i = 0;
            for (Class<?> classe : method.getParameterTypes()) {
                i++;
                logger.debug(String.format("     type arg %s  : %s", i, classe.getName()));
            }
            ;
        }

    }

    private static Method retrieveSetter(String attribute, Class<? extends Object> type) {

        String setterName = "set" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1).toLowerCase();
        Class<?>[] argTypes = { String.class };
        Method toReturn = null;
        try {
            toReturn = type.getMethod(setterName, argTypes);
        } catch (NoSuchMethodException e) {
        } catch (SecurityException e) {
            logger.error("Getting attribute setter {} for type {} : {}", attribute, type.getName(), e.toString());
        }
        return toReturn;

    }
}
