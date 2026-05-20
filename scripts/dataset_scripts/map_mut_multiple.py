# python3 scripts/map_mut_multiple.py --inputs projects_decomposed/commons-beanutils/dataset/inputs.csv --meta projects_decomposed/commons-beanutils/dataset/meta.csv --inputs-mul projects_decomposed/commons-beanutils/dataset_multiple/inputs_multiple.csv --meta-mul projects_decomposed/commons-beanutils/dataset_multiple/meta_multiple.csv --output projects_decomposed/commons-beanutils/dataset_multiple/inputs_multiple_mapped.csv
import csv
import re
import argparse
from collections import defaultdict


def read_csv(path):
    with open(path, newline='', encoding='utf-8') as f:
        return list(csv.DictReader(f))


def normalise(raw):
    stripped = re.sub(r'_\d+_oe$', '', raw)
    return stripped if stripped.endswith('_oe') else stripped + '_oe'


def main(args):
    inputs   = read_csv(args.inputs)
    meta     = read_csv(args.meta)
    inputs_m = read_csv(args.inputs_mul)
    meta_m   = read_csv(args.meta_mul)

    id_to_project = {r['id']: r['project'] for r in meta}
    meta_m_by_id  = {r['id']: r for r in meta_m}

    serial_index = defaultdict(list)
    for row in inputs:
        key = (id_to_project.get(row['id'], ''), normalise(row['test_name']))
        serial_index[key].append(row)
    for key in serial_index:
        serial_index[key].sort(key=lambda r: r['id'])

    with open(args.output, 'w', newline='', encoding='utf-8') as f:
        writer = csv.DictWriter(f, fieldnames=['id', 'test_prefix', 'test_name', 'focal_methods', 'docstrings'])
        writer.writeheader()
        for mrow in inputs_m:
            project = meta_m_by_id.get(mrow['id'], {}).get('project', '')
            matched = serial_index.get((project, mrow['test_name']), [])


            focal_methods = list(dict.fromkeys(r['focal_method'] for r in matched))
            docstrings = list(dict.fromkeys(r['docstring'] for r in matched))

            writer.writerow({
                'id':            mrow['id'],
                'test_prefix':   mrow['test_prefix'],
                'test_name':     mrow['test_name'],
                'focal_methods': '\n\n'.join(focal_methods),
                'docstrings':    '\n\n'.join(docstrings),
            })

    print(f"Written to {args.output}")


if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('--inputs',     default='inputs.csv')
    parser.add_argument('--meta',       default='meta.csv')
    parser.add_argument('--inputs-mul', default='inputs_multiple.csv')
    parser.add_argument('--meta-mul',   default='meta_multiple.csv')
    parser.add_argument('--output',     default='inputs_multiple_mapped.csv')
    main(parser.parse_args())