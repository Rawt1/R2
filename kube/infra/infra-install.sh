#!/bin/bash

SCRIPTPATH="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"

istioctl install -y -f "${SCRIPTPATH}/istio-operator.yaml" &&
kubectl wait --for=condition=ready pod -l app=istiod -n istio-system &&
echo "Istio is ready" &&
kubectl apply -f "${SCRIPTPATH}/namespaces" &&
kubectl apply -f "${SCRIPTPATH}/peer-authn.yaml" &&
echo "Namespaces are ready" &&
kubectl apply -k "${SCRIPTPATH}" &&
echo "Flagger is ready"
