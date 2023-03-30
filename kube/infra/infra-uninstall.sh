#!/usr/bin/env bash

SCRIPTPATH="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"

istioctl uninstall -y --purge &&
echo "Istio is uninstalled" &&
kubectl delete -f "${SCRIPTPATH}/namespaces" &&
echo "Namespaces are deleted" &&
kubectl delete -k "${SCRIPTPATH}" &&
echo "Flagger is deleted"