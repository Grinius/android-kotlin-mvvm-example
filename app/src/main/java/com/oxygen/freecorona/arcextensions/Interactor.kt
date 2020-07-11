package com.oxygen.freecorona.arcextensions

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Interactors represents use cases.
 * Each use case, which uses data layer, MUST implement one of these interfaces.
 */

@Suppress("UNUSED")
interface Interactor {

    /**
     * Returns an infinite stream of objects.
     * @param Params required parameters for retrieving.
     * @param Object type of object to be returned.
     */
    interface RetrieveStreamInteractorWithParams<in Params, Object> : Interactor {
        fun getStream(params: Params): Observable<Object>
    }

    /**
     * Returns an infinite stream of objects.
     * @param Object type of object to be returned.
     */
    interface RetrieveStreamInteractor<Object> : Interactor {
        fun getStream(): Observable<Object>
    }

    /**
     * Returns an Single of object.
     * @param Params required parameters for retrieving.
     * @param Object type of object to be returned.
     */
    interface RetrieveSingleInteractorWithParams<in Params, Object> : Interactor {
        fun getSingle(params: Params): Single<Object>
    }

    /**
     * Returns an Single of object.
     * @param Object type of object to be returned.
     */
    interface RetrieveSingleInteractor<Object> : Interactor {
        fun getSingle(): Single<Object>
    }

    /**
     * Synchronously returns an object.
     * @param Params required parameters for retrieving.
     * @param Object type of object to be returned.
     */
    interface SyncRetrieveInteractorWithParams<in Params, Object> : Interactor {
        fun getObject(params: Params): Object
    }

    /**
     * Synchronously returns an object.
     * @param Object type of object to be returned.
     */
    interface SyncRetrieveInteractor<Object> : Interactor {
        fun getObject(): Object
    }

    /**
     * Requests data layer for any operation.
     * @param Params required parameters for request.
     */
    interface RequestInteractorWithParams<Params> : Interactor {
        fun request(params: Params): Completable
    }

    /**
     * Requests data layer for any operation.
     */
    interface RequestInteractor : Interactor {
        fun request(): Completable
    }

    /**
     * Synchronously requests data layer for any operation.
     * @param Params required parameters for request.
     */
    interface SyncRequestInteractorWithParams<Params> : Interactor {
        fun request(params: Params)
    }

    /**
     * Synchronously requests data layer for any operation.
     */
    interface SyncRequestInteractor : Interactor {
        fun request()
    }
}