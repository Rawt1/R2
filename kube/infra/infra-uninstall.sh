#!/usr/bin/env bash

SCRIPTPATH="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"

istioctl uninstall -f "${SCRIPTPATH}/istio-operator.yaml" --purge &&
echo "Istio is uninstalled"